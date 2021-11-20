;;; Sierra Script 1.0 - (do not remove this comment)
(script# 803)
(include sci.sh)
(use Main)
(use PolyPath)
(use Motion)
(use System)

(public
	sFallsBackSide 0
	sFallsStomach 1
	sSlippery 2
	sWalksDown 3
)

(local
	gTheObj_2CycleSpeed
	local1
)
(instance sFallsBackSide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setStep: 1 1 setMotion: PolyPath 73 80 self)
			)
			(1
				(ego
					view: 805
					setLoop: 0 1
					setCel: 0
					posn: 86 91
					cycleSpeed: 9
					setCycle: End self
				)
			)
			(2
				(ego
					setLoop: 2 1
					setCel: 0
					posn: 107 106
					setCycle: 0
					setStep: 10 10
					setMotion: MoveTo 70 124 self
				)
			)
			(3
				(ego setLoop: 1 1 setCel: 1 posn: 45 (ego y?))
				(= ticks 6)
			)
			(4
				(ego
					setCel: 0
					posn: 55 130
					ignoreActors: 1
					setMotion: MoveTo 85 170 self
				)
			)
			(5
				(ego
					view: 40
					setLoop: 0 1
					setCel: 5
					posn: 102 165
					setCycle: Beg self
				)
			)
			(6
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFallsStomach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setStep: 3 2 normalize:)
				(= ticks 6)
			)
			(1
				(ego
					view: 805
					setLoop: 3 1
					setCel: 0
					posn: 105 180
					setCycle: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(2
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSlippery of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0 setStep: 1 1)
				(self cue:)
			)
			(1
				(if local1
					(self cue:)
				else
					(messager say: 15 6 5 0 self)
				)
			)
			(2
				(= local1 1)
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 15) self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWalksDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setStep: 3 2)
				(self cue:)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 15) self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
