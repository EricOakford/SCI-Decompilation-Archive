;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room91 0
)

(local
	henchman
)
(instance theMusic of Sound)

(instance Room91 of Room
	(properties
		picture 91
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 141)
		(Load VIEW 636)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		((Prop new:)
			isExtra: TRUE
			view: 636
			loop: 0
			posn: 133 60
			setPri: 15
			init:
			setCycle: Forward
		)
		(if (or (== prevRoomNum 92) (== prevRoomNum 0))
			(ego posn: 291 152 view: 4 setStep: 4 2 init:)
			(if henchChasingEgo
				(ego x: 275)
				((= henchman (Actor new:))
					view: 141
					posn: (+ (ego x?) 25) (ego y?)
					illegalBits: 0
					setStep: 6 4
					setCycle: Walk
					init:
					setScript: henchChase
				)
			)
		)
		(if (== prevRoomNum 89)
			(ego posn: 253 123 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 90)
			(ego posn: 27 153 view: 4 setStep: 4 2 init:)
			(if henchChasingEgo
				((= henchman (Actor new:))
					view: 141
					posn: (- (ego x?) 30) (ego y?)
					illegalBits: 0
					setStep: 6 4
					setCycle: Walk
					init:
					setScript: henchChase
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) cBROWN)
			(curRoom newRoom: 90)
		)
		(if (& (ego onControl: 0) cMAGENTA)
			(curRoom newRoom: 92)
		)
		(if (& (ego onControl: 0) cRED)
			(curRoom newRoom: 89)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[/noword]')
								(Said 'look[<around][/room,castle]')
							)
							(Print 91 0)
						)
						((Said 'look>')
							(cond 
								((Said '<under/table')
									(Print 91 1)
								)
								((Said '/table')
									(Print 91 2)
								)
								((Said '<behind,under/tapestries')
									(Print 91 3)
								)
								((Said '/tapestries,painting')
									(Print 91 4)
								)
								((Said '/chandelier,candle,candelabra')
									(Print 91 5)
								)
								((Said '/door')
									(Print 91 6)
								)
								((Said '/wall')
									(Print 91 7)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 91 8)
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						((Said 'sit>')
							(Print 91 9)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (or (>= (henchChase state?) 1) (== n 81))
			(= henchChasingEgo TRUE)
			(= n 81)
		else
			(= henchChasingEgo FALSE)
			(User canControl: TRUE canInput: TRUE)
		)
		(henchChase seconds: 0)
		(super newRoom: n)
	)
)

(instance henchChase of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 41 play:)
				(client setMotion: Chase ego 15 self)
				(= henchChasingEgo TRUE)
			)
			(1
				(if (== curRoomNum newRoomNum)
					(= inCutscene TRUE)
					(User canControl: FALSE canInput: FALSE)
					(ego moveSpeed: FALSE setMotion: FALSE)
					(theMusic number: 42 play:)
					(= seconds 4)
				)
			)
			(2
				(if (== curRoomNum newRoomNum)
					(curRoom newRoom: 81)
				else
					(User canControl: TRUE canInput: TRUE)
					(= inCutscene FALSE)
				)
			)
		)
	)
)
