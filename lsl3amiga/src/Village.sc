;;; Sierra Script 1.0 - (do not remove this comment)
(script# VILLAGE)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm299 0
)

(instance rm299 of Locale
	(method (init)
		(super init:)
		(if
			(not
				(OneOf prevRoomNum
					200 203 210 213 216 220 230 235
					240 245 250 253 300 305 310
				)
			)
			(music stop: number: 299 loop: musicLoop play:)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man,couple,babe')
				(Print 299 0)
			)
			((Said 'carve,get/blade')
				(Print 299 1)
			)
			((Said '/cab')
				(Print 299 2)
			)
			((Said '/auto,auto')
				(Print 299 3)
				(Print 299 4 #at -1 144)
			)
			((Said 'climb/palm')
				(Print 299 5)
			)
			((Said 'drain,(get<off)/sandal')
				(Printf 299 6
					(if playingAsPatti
						{rip your nylons}
					else
						{get your white socks dirty}
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/up,air')
						(Print 299 7)
					)
					((Said '/auto,camp')
						(Print 299 8)
					)
					((Said '/carpet')
						(Print 299 9)
					)
					((Said '/palm,bush')
						(Print 299 10)
					)
				)
			)
		)
	)
)
