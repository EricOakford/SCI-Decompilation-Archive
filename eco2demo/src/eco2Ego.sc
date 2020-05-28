;;; Sierra Script 1.0 - (do not remove this comment)
(script# ECO2EGO)
(include game.sh)
(use Main)
(use StopWalk)
(use Ego)


(class Eco2Ego of Ego
	(properties
		name {ego}
	)
	
	(method (init)
		(super init:)
		(self signal: (| signal skipCheck) setCycle: 0)
	)
	
	(method (put)
		(if (== (theIconBar curIcon?) (theIconBar at: 5))
			(StartARoom 0)
		)
		(super put: &rest)
	)
	
	(method (normalize theView theLoop &tmp temp0)
		(if argc
			(= view theView)
			(if (> argc 1) (= loop theLoop))
		)
		(= temp0
			(switch loop
				(6 45)
				(0 90)
				(4 135)
				(2 180)
				(5 225)
				(1 270)
				(7 315)
			)
		)
		(self
			heading: temp0
			setLoop: -1
			setLoop: (ScriptID 0 8)
			setCel: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			setStep: 3 2
			z: 0
			illegalBits: cWHITE
			ignoreActors: 0
			setSpeed: egoSpeed
		)
	)
)
