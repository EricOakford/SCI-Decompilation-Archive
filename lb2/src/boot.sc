;;; Sierra Script 1.0 - (do not remove this comment)
(script# 443)
(include game.sh)
(use Main)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	boot 0
	blood 1
	inBoot 2
	inPippin 3
	armorPippin 4
)

(local
	local0
)
(instance sFindPippin of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 442
					loop: 0
					cel: 0
					posn: 129 162
					setScale: Scaler 100 100 190 90
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				(noise number: 440 play:)
				(ego setCycle: EndLoop self)
			)
			(3 (= seconds 3))
			(4
				(theGame handsOn:)
				(= local0 1)
				(ego
					loop: 6
					normalize: (if (== currentAct 5) 426 else 831)
					setScale: Scaler 155 0 190 90
				)
				(curRoom setInset: inPippin self)
			)
			(5 (self dispose:))
		)
	)
)

(instance sGetBoot of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 440
					setScale: Scaler 100 100 190 90
					setLoop: 6
					cycleSpeed: 16
					setCycle: EndLoop self
				)
			)
			(2
				(boot dispose:)
				(ego get: iWorkBoot)
				((ScriptID 21 0) doit: 781)
				(ego setCycle: BegLoop self)
			)
			(3
				(theGame points: 1 150)
				(= cycles 1)
			)
			(4
				(ego
					loop: 5
					posn: (+ (ego x?) 2) (ego y?)
					setScale: Scaler 155 0 190 90
					normalize: (if (== currentAct 5) 426 else 831)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boot of View
	(properties
		x 85
		y 169
		approachX 100
		approachY 162
		view 440
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGame points: 1 150)
				(curRoom setInset: inBoot)
			)
			(4
				(curRoom setScript: sGetBoot)
			)
			(8
				(theGame points: 1 150)
				(curRoom setInset: inBoot)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blood of View
	(properties
		x 89
		y 172
		noun 34
		approachX 100
		approachY 162
		view 440
		loop 1
		signal $4000
	)
)

(instance inBoot of Inset
	(properties
		view 440
		cel 1
		x 57
		y 154
		priority 15
		disposeNotOnMe 1
		modNum 15
		noun 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetBoot)
				(inBoot dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inPippin of Inset
	(properties
		view 442
		loop 1
		x 100
		y 40
		priority 15
		disposeNotOnMe 1
		noun 11
	)
	
	(method (dispose)
		(noise dispose:)
		(super dispose:)
	)
)

(instance armorPippin of Feature
	(properties
		x 151
		y 128
		noun 10
		nsTop 96
		nsLeft 140
		nsBottom 160
		nsRight 164
		sightAngle 40
		approachX 128
		approachY 165
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(cond 
						(local0
							(if (MuseumRgn nobodyAround:)
								(curRoom setInset: inPippin)
							else
								(return 1)
							)
						)
						((>= currentAct 3) (messager say: 10 1 1))
						(else (messager say: 10 1 2))
					)
				)
				(8
					(if (>= currentAct 3)
						(messager say: 10 8 1)
					else
						(messager say: 10 8 2)
					)
				)
				(4
					(cond 
						(local0 (curRoom setInset: inPippin))
						((>= currentAct 3)
							(if (not (== (ego view?) 443))
								(if (MuseumRgn nobodyAround:)
									(curRoom setScript: sFindPippin)
								else
									(return 1)
								)
							else
								(super doVerb: theVerb)
							)
						)
						(else (messager say: 10 4 2))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
