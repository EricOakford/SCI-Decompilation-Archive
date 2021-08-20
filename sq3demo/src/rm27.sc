;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm27 0
)

(instance rm27 of Room
	(properties
		picture 27
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 39)
		(Load VIEW 52)
		(Load SOUND 16)
		(enterprise init: stopUpd:)
		(super init:)
		(ship init:)
		(= enterpriseState 3)
		(theMusic number: 16 loop: -1 play:)
		(self setScript: arrivalScript)
	)
)

(instance arrivalScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ship
					setCycle: EndLoop
					setStep: 3 1
					setMotion: MoveTo 201 98 self
				)
			)
			(1
				(ship
					posn: 201 98
					setStep: 1 1
					setCycle: 0
					setMotion: MoveTo 205 98 self
				)
			)
			(2
				(ship setMotion: MoveTo 215 108 self)
			)
			(3
				(ship setMotion: MoveTo 215 116 self)
			)
			(4
				(ship setMotion: MoveTo 208 122 self)
				(client setScript: entScript)
			)
			(5
				(ship setMotion: MoveTo 198 122)
			)
		)
	)
)

(instance entScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(enterprise setMotion: MoveTo 92 107 self)
			)
			(1
				(enterprise setMotion: MoveTo 82 97 self)
			)
			(2 (= seconds 2))
			(3
				(enterprise setStep: 3 3 setMotion: MoveTo 76 103 self)
			)
			(4
				(enterprise setCycle: EndLoop self)
			)
			(5
				(enterprise dispose:)
				(= enterpriseState 1)
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance ship of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 52
			cel: 0
			posn: 60 145
			setPri: 4
			ignoreActors: TRUE
			illegalBits: 0
		)
	)
)

(instance enterprise of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 39
			cel: 0
			posn: 122 107
			setStep: 1 1
			setPri: 6
			setCycle: 0
			ignoreActors: TRUE
			illegalBits: 0
			cycleSpeed: 1
		)
	)
)
