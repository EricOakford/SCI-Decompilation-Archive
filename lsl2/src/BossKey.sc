;;; Sierra Script 1.0 - (do not remove this comment)
(script# BOSSKEY)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Menu)
(use System)

(public
	rm9 0
)

(instance rm9 of Room
	(properties
		picture 178
	)
	
	(method (init)
		(super init:)
		(TheMenuBar hide:)
		(self setScript: rm9Script)
		(ego posn: 99 1098 setMotion: 0)
		(User canControl: FALSE canInput: TRUE)
		(Animate 0)
		(Display 9 0 p_at 0 20 p_font 999 p_color vGREEN)
		(Display 9 1 p_at 0 28 p_font 999 p_color vGREEN)
		(Display 9 2 p_at 0 36 p_font 999 p_color vGREEN)
		(Display 9 3 p_at 0 44 p_font 999 p_color vGREEN)
		(Display 9 4 p_at 0 60 p_font 999 p_color vGREEN)
		(Display 9 5 p_at 0 76 p_font 999 p_color vGREEN)
		(Display 9 6 p_at 0 84 p_font 999 p_color vGREEN)
		(Display 9 7 p_at 0 92 p_font 999 p_color vGREEN)
		(Display 9 8 p_at 50 108 p_font 999 p_color vGREEN)
		(Display 9 9 p_at 50 116 p_font 999 p_color vGREEN)
		(Display 9 10 p_at 50 124 p_font 999 p_color vGREEN)
	)
)

(instance rm9Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (== (event type?) mouseUp) (event claimed?))
			(return)
		)
		(Print 9 11)
		(Print 9 12 #at -1 152)
		(event claimed: TRUE)
	)
)
