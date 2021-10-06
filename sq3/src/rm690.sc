;;; Sierra Script 1.0 - (do not remove this comment)
(script# 690)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm690 0
)

(instance rm690 of Room
	(properties
		picture 690
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: FALSE)
		(super init:)
		(if (not forceBeamDestroyed)
			(Load VIEW 91)
			(ray init:)
		)
		(self setScript: RayScript)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[/around,scope]')
						(Print 690 0)
					)
					((Said '/moon')
						(Print 690 1)
					)
					((Said '/beam')
						(cond 
							(forceBeamDestroyed
								(Print 690 2)
							)
							(decodedMessage
								(Print 690 3)
							)
							(else
								(Print 690 4)
							)
						)
					)
				)
			)
			((Said 'get>')
				(event claimed: TRUE)
				(Print 690 5)
			)
		)
	)
)

(instance RayScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 5)
			)
			(1
				(cond 
					((not lookedAtForceBeam)
						(cond 
							(decodedMessage
								(Print 690 6)
							)
							(forceBeamDestroyed
								(Print 690 7)
							)
							(else
								(Print 690 8)
							)
						)
						(theGame changeScore: 10)
					)
					(forceBeamDestroyed
						(if decodedMessage
							(Print 690 9)
						else
							(Print 690 10)
						)
					)
					(decodedMessage
						(Print 690 11)
					)
					(else
						(Print 690 12)
					)
				)
				(= lookedAtForceBeam TRUE)
				(= seconds 5)
			)
			(2
				(++ ortegaPostBeamRooms)
				(client newRoom: 69)
			)
		)
	)
)

(instance ray of Prop
	(method (init)
		(super init:)
		(self
			view: 91
			setLoop: 0
			setCel: 0
			setPri: 11
			posn: 145 85
			setCycle: Forward
			cycleSpeed: 0
		)
	)
)
