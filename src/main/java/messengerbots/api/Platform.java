package messengerbots.api;

import messengerbots.internal.ChatImpl;

public enum Platform {
	
	VK {
		@Override
		public IChat chat(final BotAPI api, final long id) {
			return new ChatImpl.Vk(api, id);
		}
		
		@Override
		public IChat chat(final BotAPI api, final String id) {
			try {
				return this.chat(api, Long.valueOf(id));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("VK id must be long-like, provided id is '" + id + "'");
			}
		}
	},
	
	TG {
		@Override
		public IChat chat(final BotAPI api, final long id) {
			return this.chat(api, Long.toString(id));
		}
		
		@Override
		public IChat chat(final BotAPI api, final String id) {
			return new ChatImpl.Tg(api, id);
		}
	},
	
	;
	
	public abstract IChat chat(BotAPI api, long id);
	
	public abstract IChat chat(BotAPI api, String id);
}
