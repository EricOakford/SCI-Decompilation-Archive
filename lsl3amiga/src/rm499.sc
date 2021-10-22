;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASINO)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm499 0
)

(instance rm499 of Locale
	(method (init)
		(super init:)
		(if (not (OneOf prevRoomNum 400 410 415 416 420 460))
			(music number: 499 loop: musicLoop play:)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'address/man,couple,babe')
				(Print 499 0)
			)
			((Said 'drain,(get<off)/sandal')
				(Printf 499 1
					(if playingAsPatti
						{rip your nylons}
					else
						{get those white socks dirty}
					)
				)
			)
			((Said 'gamble')
				(Print 499 2)
			)
			((Said '/art,art')
				(Print 499 3)
			)
			((Said '(look<up),look[/ceiling]')
				(Print 499 4)
			)
			((Said '/casino,gambling,building')
				(Print 499 5)
			)
			((Said 'look>')
				(cond 
					((Said '/building,casino,area')
						(Print 499 6)
					)
					((Said '/auto,camp')
						(Print 499 7)
					)
					((Said '/carpet')
						(Print 499 8)
						(Print 499 9
							#at -1 144
						)
					)
				)
			)
			((Said '/casino')
				(Print 499 10)
			)
		)
	)
)
