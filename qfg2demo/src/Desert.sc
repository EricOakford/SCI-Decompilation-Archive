;;; Sierra Script 1.0 - (do not remove this comment)
(script# DESERT) ;7
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use DPath)
(use Grooper)
(use Motion)
(use Game)
(use System)

(public
	demoDesert 0
)

(instance demoDesert of Room
	(properties
		picture 660
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW 12)
		(Load SOUND 790)
		(super init:)
		(globalSound stop: number: 790 loop: 1 playBed:)
		(self setScript: rmScript)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print DESERT 0 #at -1 170 #dispose)
				(ego
					view: 12
					loop: 0
					cel: 0
					posn: -20 164
					init:
					setStep: 5 3
					cycleSpeed: 0
					setCycle: Forward
					setLoop: GradualLooper
					setMotion: DPath 121 139 145 124 self
					setPri: -1
					illegalBits: 0
				)
			)
			(1
				(ego setPri: 2 setLoop: setMotion: MoveTo 157 151 self)
			)
			(2
				(ego setCycle: 0)
				(cls)
				(globalSound fade:)
				(curRoom newRoom: SCORP)
				(self dispose:)
			)
		)
	)
)
