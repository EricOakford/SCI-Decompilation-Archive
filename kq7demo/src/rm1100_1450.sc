;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1450)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Print)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm1100 0
)

(instance rm1100 of KQRoom
	(properties
		picture 1450
		style $000e
		exitStyle 13
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						24 121
						73 105
						107 79
						149 80
						145 61
						170 60
						177 72
						186 70
						210 90
						289 83
						318 107
						171 147
						5 135
					yourself:
				)
		)
		(Bset 21)
		(ego init: posn: 127 113)
		(if (not (Btst 23))
			(scorpion init:)
		)
		(cond 
			(demoScripts (self setScript: demo))
			((not (Btst 36)) (self setScript: seeTheScorpion))
		)
		(exitPyramid init:)
		(altar init:)
		(if (ego has: 71) (lightShaft init:))
		(if
			(and
				(== ((ScriptID 0 6) seconds?) -1)
				(Btst 20)
				(not (Btst 23))
			)
			((ScriptID 0 6) setReal: (ScriptID 0 6) 360)
		)
	)
	
	(method (dispose)
		(if (ego has: 4)
			(ego put: 4)
			(ego get: 3)
			(ego get: 1)
		)
		(DisposeScript -597)
		(super dispose:)
	)
	
	(method (cue)
		(self setScript: killEgo)
	)
)

(instance waveFlag of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 22 135 self)
			)
			(1
				(if (not (Platform 2))
					(theSoundFX
						number: 1451
						setVol: 127
						setLoop: -1
						play: self
					)
				)
				(ego view: 1454 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(scorpion
					posn: 64 134
					view: 1454
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(scorpion setLoop: 2 setCel: 0 setCycle: Forward)
				(self cue:)
			)
			(4
				(Bset 20)
				((ScriptID 0 6) setReal: (ScriptID 0 6) 360)
				(ego put: 4 get: 3 normalize: ignoreActors: 1)
				(peticoat init:)
				(theGame handsOn:)
			)
		)
	)
)

(instance reduceScorp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(scorpion
					view: 1456
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(scorpion setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(scorpion dispose:)
				(ego put: 18)
				(Bclr 44)
				(Bset 23)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance scorpRunsAway of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(Prints {The scorpion runs away squealing.})
				(Bclr 206)
				(scorpion dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stungScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints {You wave your hands in a stay back manner.})
				(self cue:)
			)
			(1 (messager say: 1 8 0 0 self))
			(2
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3 (EgoDead 3 self))
			(4
				(scorpion view: 1453 setLoop: 1 setCel: 0 posn: 102 98)
				(ego posn: 127 113)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance killEgo of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(scorpion
					view: 1455
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1 (EgoDead 4 self))
			(2
				(scorpion view: 1454 setLoop: 2 setCel: 0 setCycle: Forward)
				(Bset 20)
				((ScriptID 0 6) setReal: (ScriptID 0 6) 360)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance seeTheScorpion of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 112 113 self)
			)
			(1
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(2
				(Prints {The scorpion threatens you.})
				(self cue:)
			)
			(3
				(Bset 36)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance demo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load RES_VIEW 1454)
				(if (not (Platform PlatWin))
					(theSoundFX number: 1459 setVol: 127 setLoop: 1 play:)
				)
				(ego
					view: 1454
					setLoop: 0
					setCel: 0
					x: 22
					y: 135
					init:
					setCycle: Forward
				)
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(1
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(2
				(scorpion
					posn: 64 134
					view: 1454
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (not (Platform 2))
					(theSoundFX number: 1459 setVol: 127 setLoop: 1 play:)
				)
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(4
				(scorpion
					posn: 64 134
					view: 1454
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
				(ego setCycle: EndLoop)
			)
			(5
				(if (not (Platform 2))
					(theSoundFX number: 1459 setVol: 127 setLoop: 1 play:)
				)
				(scorpion setLoop: 2 setCel: 0 setCycle: Forward)
				(= seconds 4)
			)
			(6
				(if (not (Platform 2)) (theSoundFX stop:))
				(if scrollingIsOn
					(curRoom newRoom: 9999)
				else
					(Bclr 21)
					(curRoom newRoom: 30)
				)
			)
		)
	)
)

(instance wavePeticoat of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 1453 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(scorpion
					view: 1453
					setLoop: 1
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(2
				(if (Btst 45)
					(self cue:)
				else
					(Bset 45)
					(messager say: 1 6 1 0 self)
				)
			)
			(3
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance scorpion of Actor
	(properties
		noun 1
		x 102
		y 98
		view 1453
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 20)
			(self
				posn: 64 134
				view: 1454
				setLoop: 2
				setCel: 0
				setCycle: Forward
			)
		else
			(= x 102)
			(= y 98)
			(if (not (ego has: 5))
				(self setHotspot: 8 13 10 26 5 6 87)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom setScript: waveFlag)
			)
			(26
				(curRoom setScript: reduceScorp)
			)
			(6
				(curRoom setScript: wavePeticoat)
			)
			(87
				(if (Btst 206)
					(curRoom setScript: scorpRunsAway)
				else
					(Prints {The uncharged crystal has no effect})
					(curRoom setScript: stungScript)
				)
			)
			(else 
				(curRoom setScript: stungScript)
			)
		)
	)
)

(instance peticoat of View
	(properties
		y 80
		view 1000
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(ego get: 1)
				((inventory at: 1) setHotspot: 0 11)
				(self dispose:)
				(return 1)
			else
				0
			)
		)
	)
)

(instance altar of Feature
	(properties
		nsLeft 209
		nsTop 58
		nsRight 272
		nsBottom 84
		approachX 240
		approachY 90
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(if (or (Btst 20) (Btst 23))
					(curRoom newRoom: 1460)
					(return 1)
				else
					(Prints
						{Bad move, you would die in the shipping version.}
					)
					(return 1)
				)
			else
				0
			)
		)
	)
)

(instance lightShaft of Feature
	(properties
		nsLeft 200
		nsRight 231
		nsBottom 30
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 87)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8 (Prints {light shaft}))
			(87
				(Prints {The crystal is charged.})
				(Bset 206)
			)
		)
	)
)

(instance exitPyramid of Feature
	(properties
		nsLeft 24
		nsRight 75
		nsBottom 95
		approachX 22
		approachY 89
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 approachVerbs: 8)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(curRoom newRoom: 1250)
				(return 1)
			else
				0
			)
		)
	)
)
