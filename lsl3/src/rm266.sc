;;; Sierra Script 1.0 - (do not remove this comment)
(script# 266)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm266 0
)

(local
	local0
)
(instance rm266 of Room
	(properties
		picture 266
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (< filthLevel 3)
			(addToPics add: atpBikiniTop doit:)
		)
		(addToPics add: atpBikiniBottom doit:)
		(User canInput: 1 canControl: 0)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fLookedAtTawni))
					(= seconds 3)
				)
			)
			(1
				(Bset fLookedAtTawni)
				(Print 266 10)
				(Print 266 11 #at -1 144)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'cease/look')
					(Said 'look<cease')
					(Said 'look/beach,area')
					(Said 'exit,done')
					(Said 'exit,exit,done,exit,go')
				)
				(Ok)
				(curRoom newRoom: 260)
			)
			((Said 'address,ask,say')
				(Print 266 0)
				(curRoom newRoom: 265)
			)
			((Said 'give')
				(Print 266 1)
			)
			((Said 'look>')
				(cond 
					((Said '/babe,body,maller')
						(Print 266 2)
						(Print 266 3)
					)
					((Said '/boob')
						(if (>= filthLevel 3)
							(Print 266 4)
						else
							(Print 266 5)
						)
					)
					((Said '/ass,bottom')
						(Print 266 6)
					)
					((Said '/clit,ball')
						(Print 266 7)
					)
					((Said '/eye,eye')
						(Print 266 0)
						(curRoom newRoom: 265)
					)
					((Said '/calf')
						(Print 266 8)
					)
					((Said '[/area]')
						(Print 266 9)
					)
				)
			)
			(else
				(Print 266 0)
				(curRoom newRoom: 265)
			)
		)
	)
)

(instance atpBikiniTop of PicView
	(properties
		y 56
		x 98
		view 266
	)
)

(instance atpBikiniBottom of PicView
	(properties
		y 91
		x 103
		view 266
		cel 1
	)
)
