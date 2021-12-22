;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include sci.sh)
(use Main)
(use n078)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm370 0
)

(local
	local0
	local1
	theGPEventX
	local3
)
(instance rm370 of LarryRoom
	(properties
		noun 1
		picture 370
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 65 139 90 87 176 87 210 139
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 108 103 159 103 159 116 108 116
					yourself:
				)
		)
		(super init: &rest)
		(heat init: setCycle: Fwd)
		(coals init:)
		(cond 
			((Btst 194)
				(bucket
					init:
					ignoreActors: 0
					loop: 2
					posn: 109 115
					case: 15
				)
			)
			((and (Btst 215) (not (Btst 66))) (bucket init: ignoreActors: 0))
		)
		(if (== global100 370)
			(UpdateScreenItem ((ScriptID 92 3) view: 1901))
			(Bset 74)
			(ego view: 353)
			(bucket init: ignoreActors: 0)
			(theGame changeScore: 8 215)
		)
		(if (and (Btst 215) (not (Btst 66)))
			(if (== (ego view?) 353) (theGame changeScore: 10 194))
			(= local0 1)
			(burg init:)
			(theMusic
				number: 337
				play:
				setLoop: -1
				mute: 1 4
				mute: 1 5
				mute: 1 6
				mute: 1 7
				mute: 1 8
				mute: 1 9
			)
		else
			(theMusic fade:)
		)
		(if
		(and (Btst 66) (not (Btst 257)) (not (ego has: 5)))
			(bracelet init:)
		)
		(benchSeats init:)
		(if local0 (= gLarryRoom curRoom))
		(ego init: normalize: posn: 160 150)
		(self setScript: enterRoom)
	)
	
	(method (doit &tmp egoEdgeHit)
		(= egoEdgeHit (ego edgeHit?))
		(cond 
			(script)
			((== egoEdgeHit 3) (curRoom setScript: toMudBath))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= gLarryRoom 0)
		(theIconBar disableIcon: (ScriptID 0 8) show:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(3
					(cond 
						((curRoom script?) ((curRoom script?) cue:))
						((ego script?) ((ego script?) cue:))
					)
					(= theGPEventX mouseX)
					(= local3 (- mouseY 10))
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(ego setScript: forwardScript)
	)
)

(instance forwardScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 theTheCursor)
		(switch (= state newState)
			(0
				(if (talkers size:) (messager cue: 1))
				(ego setCycle: 0)
				(= cycles 2)
			)
			(1
				(= theTheCursor theCursor)
				(theGame setCursor: normalCursor)
				(SetCursor 225 92)
				(if
					(Print
						width: 200
						font: userFont
						addTitle: 0 8 0 2 370
						addText: 0 8 0 1 50 1 370
						addIcon: 1911 0 0 0 0
						addButton: 0 9 8 0 1 50 33 370
						addButton: 1 8 8 0 1 140 33 370
						init:
					)
					(if global205 (proc79_7))
					(self cue:)
				else
					(theGame setCursor: theTheCursor)
					(= gLarryRoom curRoom)
					(theIconBar enableIcon: (ScriptID 0 8) show:)
					(self dispose:)
				)
			)
			(2
				(steamCartoon dispose:)
				(if (cast contains: steamCloud) (steamCloud dispose:))
				(cav dispose:)
				(burg dispose:)
				(bracelet init:)
				(theMusic stop:)
				(bucket
					init:
					ignoreActors: 0
					loop: 2
					posn: 109 115
					case: 15
				)
				(= cycles 2)
				(proc78_0)
			)
			(3
				(theIconBar
					enableIcon: (ScriptID 0 3) (ScriptID 0 6)
					show:
				)
				(theGame handsOn:)
				(walkHandler delete: curRoom)
				(ego normalize: 353 2 ignoreActors: 0)
				(self dispose:)
			)
		)
	)
)

(instance toMudBath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1 (curRoom newRoom: 400))
		)
	)
)

(instance steamCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(burg setCycle: CT 2 1)
				(Bset 66)
				(messager sayRange: 4 2 2 1 3 self)
			)
			(1
				(theGame handsOff:)
				(ego setSpeed: 6 setMotion: PolyPath 154 93 self)
			)
			(2
				(ego view: 371 loop: 0 setCel: 0 setCycle: End self)
				(burg setCycle: End)
			)
			(3
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(4
				(ego setScript: larryFidgets)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(theMusic mute: 0 8)
				(messager sayRange: 4 2 2 4 6 self)
			)
			(5
				(theMusic mute: 0 9)
				(messager sayRange: 4 2 2 7 8 self)
			)
			(6
				(ego setScript: 0)
				(ego setLoop: 1 setCel: 0)
				(cav
					init:
					setLoop: 0 1
					ignoreActors: 1
					setCycle: Walk
					setMotion: MoveTo 105 170 self
				)
			)
			(7 (messager say: 4 2 2 9 self))
			(8
				(cav setMotion: MoveTo 105 110 self)
			)
			(9
				(messager say: 4 2 2 10 self)
			)
			(10 (burg setCycle: Beg self))
			(11
				(burg setScript: burgGestures)
				(cav setMotion: MoveTo 104 91 self)
			)
			(12
				(ego setScript: larryFidgets)
				(cav view: 374 setCel: 0 setLoop: 6 1 setCycle: End self)
			)
			(13
				(cav
					setLoop: 2 1
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(14
				(messager sayRange: 4 2 3 1 5 self)
			)
			(15
				(cav setScript: cavGestures)
				(theMusic mute: 0 4)
				(messager sayRange: 4 2 3 6 10 self)
			)
			(16
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
			)
			(17
				(theMusic mute: 0 7)
				(messager sayRange: 4 2 7 2 4 self)
			)
			(18
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(theMusic mute: 0 6)
			)
			(19
				(cavGestures dispose:)
				(burgGestures dispose:)
				(theGame handsOff:)
				(burg loop: 1 setCel: 0 setCycle: End self)
			)
			(20
				(theMusic mute: 0 5)
				(burg loop: 2 setCel: 0 setCycle: End self)
			)
			(21
				(messager sayRange: 4 2 8 2 7 self)
				(cav setScript: cavGestures)
				(burg setScript: burgGestures)
			)
			(22
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
			)
			(23
				(messager say: 4 2 11 1 self)
			)
			(24
				(larryFidgets dispose:)
				(theGame handsOff:)
				(ego
					view: 371
					loop: 3
					setCel: 0
					setSpeed: 12
					setCycle: End self
				)
			)
			(25
				(ego
					view: 378
					setLoop: 0
					setCel: 0
					setPri: 140
					setCycle: End self
				)
				(heat dispose:)
				(bucket dispose:)
			)
			(26
				(ego view: 379 loop: 0 setCel: 0 setCycle: End self)
			)
			(27
				(sFx number: 374 loop: 1 play:)
				(messager say: 4 2 11 2 self)
			)
			(28
				(steamCloud
					view: 372
					loop: 0
					setCel: 0
					init:
					setPri: 199
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(29
				(cav dispose:)
				(burg dispose:)
				(steamCloud view: 375 setLoop: 0 setCel: 0 setCycle: Fwd)
				(messager say: 4 2 11 3 self)
			)
			(30
				(if global205 (proc79_7))
				(messager say: 4 2 11 4 self)
			)
			(31
				(messager say: 4 2 11 5 self)
			)
			(32
				(if global205 (proc79_7))
				(steamCloud
					view: 376
					setCel: 0
					cycleSpeed: 12
					setCycle: CT 2 1 self
				)
				(bucket
					case: 15
					init:
					ignoreActors: 0
					setLoop: 2
					setCel: 0
					posn: 109 118
				)
			)
			(33
				(steamCloud setCycle: End self)
				(messager say: 4 2 11 6 self)
			)
			(34)
			(35
				(ego normalize: 353 2 ignoreActors: 0)
				(bracelet init:)
				(steamCloud dispose:)
				(sFx number: 338 play:)
				(theMusic stop:)
				(= ticks 10)
			)
			(36
				(messager say: 4 2 11 7 self)
			)
			(37
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(messager sayRange: 4 2 11 8 9 self)
			)
			(38 (= cycles 2) (proc78_0))
			(39
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setSpeed: 6 setMotion: MoveTo 160 132 self)
			)
			(1
				(if (== (ego view?) 900)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(2
				(cond 
					((== (ego view?) 900) (EgoDead 8 self))
					(local0
						(ego normalize: 353 3)
						(Bset 25)
						(curRoom setScript: steamCartoon)
					)
					(else
						(Bset 25)
						(ego normalize: ignoreActors: 0)
						(theGame handsOn:)
						(self dispose:)
					)
				)
			)
			(3 (curRoom newRoom: 400))
		)
	)
)

(instance larryFidgets of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 180 360)))
			(1
				(if (forwardScript client?)
					(self cue:)
				else
					(ego loop: 2 setCel: 0 cycleSpeed: 12 setCycle: End self)
				)
			)
			(2
				(if (not (forwardScript client?)) (ego setCel: 0))
				(self init:)
			)
		)
	)
)

(instance larrySits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 154 93 self)
			)
			(1
				(ego
					view: 371
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(2
				(ego view: 371 setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(ego setLoop: 3 setCel: 0)
				(walkHandler addToFront: curRoom)
				(theGame handsOn:)
			)
			(4
				(theGame handsOff:)
				(walkHandler delete: curRoom)
				(ego
					view: 371
					setLoop: 3
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(5
				(if register
					(ego normalize: 353 2 setMotion: PolyPath 112 89 self)
				else
					(ego
						normalize: 353 2
						ignoreActors: 0
						setMotion: PolyPath theGPEventX local3
					)
					(theGame handsOn:)
					(theIconBar enableIcon: (ScriptID 0 6) show:)
					(self dispose:)
				)
			)
			(6
				(ego get: 5)
				(bracelet dispose:)
				(theGame changeScore: 12 195)
				(messager say: 7 5 0 0 self)
				(self dispose:)
			)
		)
	)
)

(instance getBracelet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 112 89 self)
			)
			(1 (= cycles 2))
			(2
				(ego get: 5)
				(bracelet dispose:)
				(theGame changeScore: 12 195)
				(messager say: 7 5 0 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cavGestures of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 180 320)))
			(1
				(cav
					loop: (if (Random 0 1) 3 else 5)
					setCel: 0
					setCycle: End self
				)
			)
			(2 (self init:))
		)
	)
)

(instance burgGestures of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 180 320)))
			(1
				(burg
					loop: (if (Random 0 1) 1 else 2)
					setCel: 0
					setCycle: End self
				)
			)
			(2 (self init:))
		)
	)
)

(instance benchSeats of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						86
						138
						55
						138
						53
						89
						105
						67
						195
						69
						241
						81
						240
						139
						211
						138
						226
						117
						209
						91
						184
						85
						119
						82
						75
						92
						71
						124
						86
						137
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (curRoom script?))
					(curRoom setScript: larrySits)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance burg of Prop
	(properties
		noun 4
		x 129
		y 76
		view 373
		cel 1
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(switch (++ local1)
						(1
							(messager say: 4 2 7 1 steamCartoon)
						)
						(2
							(messager say: 4 2 8 1 steamCartoon)
						)
						(3
							(cavGestures dispose:)
							(cav setLoop: 4 1 setCel: 0 setCycle: End)
							(messager say: 4 2 9)
						)
						(4
							(cav setScript: cavGestures)
							(burgGestures dispose:)
							(messager sayRange: 4 2 10 1 2 self)
						)
						(5
							(steamCartoon cue:)
							(return 1)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (cue)
		(if (cast contains: bracelet)
			(burg setScript: burgGestures)
		else
			(burg setScript: doTheBracelet)
		)
	)
)

(instance doTheBracelet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(burg view: 373 setLoop: 3 setCel: 0 setCycle: End self)
			)
			(1
				(bracelet init:)
				(messager sayRange: 4 2 10 3 6 self)
			)
			(2 (burg cue:) (self dispose:))
		)
	)
)

(instance heat of Prop
	(properties
		noun 12
		x 135
		y 103
		priority 108
		fixPriority 1
		view 370
		cel 3
		signal $6821
		cycleSpeed 12
	)
)

(instance bucket of View
	(properties
		noun 11
		x 134
		y 89
		view 370
		loop 1
	)
)

(instance cav of Actor
	(properties
		noun 5
		x 100
		y 200
		view 374
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (burg doVerb: 2))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance steamCloud of Prop
	(properties
		noun 10
		x 126
		y 106
		view 376
		cycleSpeed 12
	)
)

(instance bracelet of View
	(properties
		noun 7
		approachX 112
		approachY 89
		x 104
		y 73
		view 377
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(5
					(if (curRoom script?)
						((curRoom script?) register: 1 cue:)
						(return 1)
					else
						(curRoom setScript: getBracelet)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance coals of Feature
	(properties
		noun 12
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 114 108 113 99 121 93 148 94 154 98 154 108 140 113 126 112
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sFx of Sound
	(properties
		flags $0001
	)
)

(instance talkTimer of Timer
	(properties)
)
