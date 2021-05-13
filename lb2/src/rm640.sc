;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use Inset)
(use Scaler)
(use PolyPath)
(use Feature)
(use ForCount)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm640 0
	westDoor 4
)

(local
	local0
	local1
	local2
	local3
)
(instance rm640 of LBRoom
	(properties
		noun 29
		picture 640
		west 610
		vanishingY 85
	)
	
	(method (init)
		(ego init: normalize: 831 setScale: 155)
		(self setRegions: 90)
		(switch prevRoomNum
			(west
				(ego edgeHit: 0 setHeading: 90)
			)
			(else 
				(ego wearingGown: 1 posn: 160 160)
				(Bset 4)
				(theGame handsOn:)
			)
		)
		(super init:)
		(LoadMany 132 640 641 643)
		(westDoor init:)
		(if (not (Btst 4))
			(Load rsVIEW 646)
			(cond 
				((not (TriggerEvent -20222))
					(Load rsPIC 645)
					(theMusic2 number: 551 flags: 1 loop: -1 play:)
					(ernie_Yvette init: cycleSpeed: 42 setCycle: Fwd)
					(Bset 18)
					(toolBoxClosed init:)
					(desk init:)
					(underDesk init:)
					(intercom init:)
					(mopBucket init:)
					(bear init: setOnMeCheck: 1 4)
					(skeleton init: setOnMeCheck: 1 2)
					(brooms init:)
					(block init: setOnMeCheck: 1 8)
					(blotchOnWall init:)
					(light init: setOnMeCheck: 1 16)
					(nautilus init:)
					(nefertiti init:)
					(squirrel init:)
					(heads init:)
					(beam1 init:)
					(beam2 init:)
					(leftStuff init:)
					(rightStuff init:)
					(rightmostStuff init: setOnMeCheck: 1 32)
					(blender init:)
					(snakeLasso ignoreActors: 1 approachVerbs: 4 1 8 init:)
				)
				((not (TriggerEvent 4880))
					(theMusic2 number: 642 flags: 1 loop: -1 play:)
					(if (or (Btst 15) (Random 0 1))
						(LoadMany 128 642 643)
						(toolBoxClosed init: approachVerbs: 4)
						(toolBoxOpen init: approachVerbs: 4 1)
						(if (not (Btst 19)) (toolBoxOpen hide:))
						(if (not (ego has: 19))
							(snakeLasso approachVerbs: 4 1 8 init:)
						)
						(desk init: approachVerbs: 1 8)
						(underDesk init: approachVerbs: 1 8)
						(intercom init:)
						(mopBucket init:)
						(bear init: setOnMeCheck: 1 4)
						(skeleton init: setOnMeCheck: 1 2)
						(brooms init:)
						(block init: setOnMeCheck: 1 8)
						(blotchOnWall
							setCel: (if (Btst 119) (blotchOnWall lastCel:) else 0)
							approachVerbs: 4
							init:
						)
						(light init: setOnMeCheck: 1 16)
						(nautilus init:)
						(nefertiti init:)
						(squirrel init:)
						(heads init:)
						(beam1 init:)
						(beam2 init:)
						(leftStuff init:)
						(rightStuff init:)
						(rightmostStuff init: setOnMeCheck: 1 32)
						(blender init:)
					else
						((ScriptID 31 0)
							init:
							view: 824
							room: curRoomNum
							setPri: 9
							posn: 108 140
							setHeading: 90
						)
						(Bset 18)
						(= local0 1)
					)
				)
				(else
					(Load rsPIC 645)
					(theMusic2 number: 551 flags: 1 loop: -1 play:)
					(ernie_Yvette
						init:
						setLoop: 1
						cycleSpeed: 42
						setCycle: Fwd
					)
					(Bset 18)
				)
			)
		else
			(LoadMany 128 642 643)
			(theMusic2 number: 642 flags: 1 loop: -1 play:)
			(toolBoxClosed init: approachVerbs: 4)
			(toolBoxOpen init: approachVerbs: 4 1)
			(if (not (Btst 19)) (toolBoxOpen hide:))
			(desk init: approachVerbs: 1 8)
			(underDesk init: approachVerbs: 1 8)
			(if (not (ego has: 19))
				(snakeLasso approachVerbs: 4 1 8 init:)
			)
			(intercom init:)
			(mopBucket init:)
			(bear init: setOnMeCheck: 1 4)
			(skeleton init: setOnMeCheck: 1 2)
			(brooms init:)
			(block init: setOnMeCheck: 1 8)
			(blotchOnWall
				setCel: (if (Btst 119) (blotchOnWall lastCel:) else 0)
				approachVerbs: 4
				init:
			)
			(light init: setOnMeCheck: 1 16)
			(nautilus init:)
			(nefertiti init:)
			(squirrel init:)
			(heads init:)
			(beam1 init:)
			(beam2 init:)
			(leftStuff init:)
			(rightStuff init:)
			(rightmostStuff init: setOnMeCheck: 1 32)
			(blender init:)
		)
	)
	
	(method (dispose)
		(if (walkHandler contains: curRoom)
			(walkHandler delete: curRoom)
		)
		(if (not (Btst 18)) (Bset 15) else (Bclr 15))
		(ernieTimer dispose: delete:)
		(theMusic2 fade:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if local3
					(roomActions doVerb:)
				else
					(curRoom setScript: sKickOut)
				)
			)
			(else 
				(if local3
					(roomActions doVerb:)
				else
					(super doVerb: theVerb)
				)
			)
		)
	)
	
	(method (cue)
		(if (or script inset (westDoor cel?))
			(ernieTimer setReal: self 10)
		else
			(curRoom setScript: sErnieKickOut)
		)
	)
)

(instance sEnterErnie1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 46)
				(ernie_Yvette setCycle: ForwardCounter 2 self)
			)
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 645 9)
				(= seconds 4)
			)
			(2
				(curRoom drawPic: 640 9)
				(cast eachElementDo: #show)
				(= ticks 90)
			)
			(3
				(ernie_Yvette
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(4
				(messager say: 27 0 1)
				(= cycles 1)
			)
			(5
				(walkHandler addToFront: curRoom)
				(cast eachElementDo: #actions roomActions)
				(westDoor actions: 0)
				(features eachElementDo: #actions roomActions)
				(= local3 1)
				(theGame handsOn:)
				(= seconds 15)
			)
			(6
				(curRoom setScript: sKickOut)
				(self dispose:)
			)
		)
	)
)

(instance sEnterErnie2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0)
				(ernie_Yvette setCycle: ForwardCounter 2 self)
			)
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 645 9)
				(= seconds 4)
			)
			(2
				(curRoom drawPic: 640 9)
				(cast eachElementDo: #show)
				(= ticks 90)
			)
			(3
				(ernie_Yvette
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(4
				(messager say: 27 0 2)
				(= ticks 60)
			)
			(5 (ego setHeading: 180 self))
			(6
				(ego
					view: 646
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setScale: Scaler 100 100 155 10
					setCycle: End self
				)
			)
			(7
				(ego normalize: 831 setScale: 155 loop: 2)
				(westDoor doVerb: 4)
				(self dispose:)
			)
		)
	)
)

(instance sErnieAloneKick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 31 0) setLoop: 7 setCel: 4)
				(= cycles 4)
			)
			(1
				((ScriptID 31 0) setCel: 2)
				(= cycles 1)
			)
			(2
				(if (Random 0 1)
					(messager say: 27 0 3)
				else
					(messager say: 27 0 6)
				)
				(= ticks 60)
			)
			(3 (ego setHeading: 180 self))
			(4
				(ego
					view: 646
					setScale: Scaler 100 100 155 1
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(5
				(ego normalize: 831 setScale: 155 loop: 2)
				(Bset 18)
				(westDoor doVerb: 4)
				(self dispose:)
			)
		)
	)
)

(instance sErnieAloneAsk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 31 0) setLoop: 7 setCel: 4)
				(= cycles 4)
			)
			(1
				((ScriptID 31 0) setCel: 2)
				(= cycles 1)
			)
			(2
				(messager say: 30)
				(= cycles 1)
			)
			(3
				(walkHandler addToFront: curRoom)
				(cast eachElementDo: #actions roomActions)
				(westDoor actions: 0)
				((ScriptID 31 0) actions: ernieActions)
				(features eachElementDo: #actions roomActions)
				(= local3 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sErnieKickOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 31 0)
					init:
					view: 824
					room: curRoomNum
					posn: (westDoor moveToX?) (westDoor moveToY?)
					setHeading: 90
				)
				(westDoor open:)
			)
			(1
				(if (and (< (ego x?) 127) (< (ego y?) 165))
					(ego setMotion: PolyPath 124 160 self)
				else
					(= cycles 1)
				)
			)
			(2
				(Face
					ego
					(+ (westDoor approachX?) 30)
					(westDoor approachY?)
				)
				((ScriptID 31 0)
					setMotion:
						PolyPath
						(+ (westDoor approachX?) 30)
						(westDoor approachY?)
						self
				)
			)
			(3
				(Face (ScriptID 31 0) ego)
				(westDoor close:)
			)
			(4
				(cond 
					((Btst 96) (messager say: 27 0 10))
					((Btst 95) (messager say: 27 0 9) (Bset 96))
					(else (messager say: 27 0 8) (Bset 95))
				)
				(= cycles 1)
			)
			(5
				(ego
					setMotion: PolyPath (westDoor approachX?) (westDoor approachY?) self
				)
			)
			(6
				(Bset 18)
				(westDoor doVerb: 4)
				(self dispose:)
			)
		)
	)
)

(instance sKickOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 0)
				(= cycles 1)
			)
			(1
				(if local2
					(messager say: 27 0 6)
				else
					(messager say: 27 0 4)
				)
				(= ticks 60)
			)
			(2 (ego setHeading: 180 self))
			(3
				(ego
					view: 646
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setScale: Scaler 100 100 15 5
					setCycle: End self
				)
			)
			(4
				(ego normalize: 831 setScale: 155 loop: 2)
				(westDoor doVerb: 4)
				(Bset 18)
				(self dispose:)
			)
		)
	)
)

(instance sPlayIntercom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(messager say: 4 0 5)
				(= cycles 1)
			)
			(2 (Bset 17) (self dispose:))
		)
	)
)

(instance sGetWireCutters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 642
					setLoop: 0
					setCel: 0
					setScale: Scaler 100 100 15 5
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1
				(ego setLoop: 1 setCel: 0 setCycle: End self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego
					setLoop: 0
					setCel: (ego lastCel:)
					setCycle: Beg self
				)
			)
			(4
				(ego get: 10 normalize: 831 setScale: 155 loop: 1)
				((ScriptID 21 0) doit: 779)
				(theGame points: 1 145)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookLasso of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(1
				(ego
					view: 643
					setLoop: 0
					setCel: 0
					setScale: Scaler 100 100 15 5
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(2 (= ticks 60))
			(3 (ego setCycle: Beg self))
			(4
				(if (ego has: 19)
					(messager say: 24 1)
				else
					(curRoom setInset: inSnakeLasso)
				)
				(ego normalize: 831 setScale: 155 loop: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetLasso of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 643
					setLoop: 3
					setCel: 0
					setScale: 100 100 15 5
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1
				(snakeLasso dispose:)
				(= ticks 30)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego get: 19 normalize: 831 setScale: 155 loop: 1)
				((ScriptID 21 0) doit: 788)
				(theGame points: 1 141)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookWireCutters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 32 172 self)
			)
			(1
				(curRoom setInset: inWireCutter)
				(self dispose:)
			)
		)
	)
)

(instance sBlotchTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 640
					setScale: 155
					setLoop: 7
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(1 (= ticks 20))
			(2
				(ego setCycle: Beg)
				(blotchOnWall setCycle: End self)
				(sFX number: 558 loop: 1 flags: 1 play:)
			)
			(3
				(sFX number: 721 loop: -1 flags: 1 play:)
				(Bset 119)
				(= ticks 180)
			)
			(4 (sFX stop:) (= cycles 1))
			(5
				(ego normalize: 831 setScale: 155 loop: 1)
				(messager say: 10 4 11)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance blotchOnWall of Prop
	(properties
		x 54
		y 103
		noun 10
		approachX 66
		approachY 145
		view 640
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 119)
					(messager say: 10 1 12)
				else
					(messager say: 10 1 11)
				)
			)
			(4
				(if (Btst 119)
					(messager say: 10 4 12)
				else
					(curRoom setScript: sBlotchTime)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ernie_Yvette of Prop
	(properties
		x 113
		y 143
		view 641
		signal $4001
		cycleSpeed 12
		name "ernie&Yvette"
	)
)

(instance roomActions of Actions
	(properties)
	
	(method (doVerb)
		(if local1
			(messager say: 30 0 7)
			(= local1 0)
			(= local2 1)
			1
		else
			(curRoom setScript: sKickOut)
		)
	)
)

(instance ernieActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 1 1 24 0 0 1893)
				1
			)
			(4
				(messager say: 1 4 24 0 0 1893)
				1
			)
			(2
				(messager say: 1 2 24 0 0 1893)
				1
			)
			(else  0)
		)
	)
)

(instance westDoor of LbDoor
	(properties
		x 38
		y 94
		noun 1
		approachX 70
		approachY 152
		view 640
		loop 5
		priority 10
		signal $0010
		entranceTo 610
		moveToX 12
		moveToY 143
		enterType 0
		exitType 0
	)
	
	(method (cue)
		(if (== (curRoom script?) sErnieKickOut)
			(if (== doorState 1) (altPolyList delete: doorPoly))
			(sErnieKickOut cue:)
		else
			(super cue:)
			(if (== doorState 0)
				(theIconBar enable: 7)
				(cond 
					((Btst 4)
						(if (and (not (Btst 17)) (not (Btst 22)))
							(curRoom setScript: sPlayIntercom)
						)
					)
					((not (TriggerEvent -20222)) (curRoom setScript: sEnterErnie1))
					((not (TriggerEvent -15612))
						(if local0
							(curRoom setScript: sErnieAloneKick)
						else
							(ernieTimer setReal: curRoom (if (Btst 96) 30 else 60))
							(if (and (not (Btst 17)) (not (Btst 22)))
								(curRoom setScript: sPlayIntercom)
							)
						)
					)
					((not (TriggerEvent 4880))
						(if local0
							(= local1 1)
							(curRoom setScript: sErnieAloneAsk)
						else
							(ernieTimer setReal: curRoom (if (Btst 96) 30 else 60))
							(if (and (not (Btst 17)) (not (Btst 22)))
								(curRoom setScript: sPlayIntercom)
							)
						)
					)
					(else (curRoom setScript: sEnterErnie2))
				)
			)
		)
	)
	
	(method (createPoly)
		(super createPoly: 13 150 40 138 47 145 22 155)
	)
)

(instance toolBoxOpen of View
	(properties
		x 1
		y 150
		noun 2
		approachX 32
		approachY 172
		view 640
		priority 13
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (ego has: 10)
					(super doVerb: theVerb)
				else
					(curRoom setInset: inWireCutter)
				)
			)
			(4
				(self hide:)
				(Bclr 19)
				(sFX number: 641 loop: 1 flags: 1 play:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance snakeLasso of View
	(properties
		x 118
		y 140
		approachX 136
		approachY 143
		view 640
		loop 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (self actions?)
					0
				else
					(curRoom setScript: sLookLasso)
				)
			)
			(8 (self doVerb: 1))
			(4 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inWireCutter of Inset
	(properties
		view 640
		loop 4
		y 133
		disposeNotOnMe 1
		noun 22
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetWireCutters)
				(self dispose:)
			)
			(1
				(messager say: 38 1 0 0 0 15)
				1
			)
			(8
				(messager say: 38 8 0 0 0 15)
				1
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inSnakeLasso of Inset
	(properties
		view 640
		loop 1
		x 81
		y 116
		disposeNotOnMe 1
		noun 23
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetLasso)
				(self dispose:)
				1
			)
			(1
				(messager say: 32 1 0 0 0 15)
				1
			)
			(8
				(messager say: 32 8 0 0 0 15)
				1
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inVatBook of Inset
	(properties
		view 640
		loop 2
		x 53
		y 100
		disposeNotOnMe 1
		noun 25
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setInset: inVatBookOpen)
				1
			)
			(8 (messager say: 25 1) 1)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inVatBookOpen of Inset
	(properties
		view 640
		loop 3
		x 53
		y 100
		disposeNotOnMe 1
		noun 26
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8 (messager say: 26 1) 1)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance toolBoxClosed of Feature
	(properties
		x 15
		y 163
		noun 2
		nsTop 156
		nsLeft 5
		nsBottom 170
		nsRight 25
		sightAngle 40
		approachX 32
		approachY 172
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (and (Btst 19) (not (ego has: 10)))
					(curRoom setScript: sLookWireCutters)
				else
					(super doVerb: theVerb)
				)
			)
			(4
				(if (Btst 19)
					(toolBoxOpen hide:)
					(Bclr 19)
					(sFX number: 641 loop: 1 flags: 1 play:)
				else
					(toolBoxOpen show: stopUpd:)
					(Bset 19)
					(sFX number: 640 loop: 1 flags: 1 play:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance desk of Feature
	(properties
		x 79
		y 134
		noun 3
		nsTop 123
		nsLeft 43
		nsBottom 145
		nsRight 116
		sightAngle 40
		approachX 79
		approachY 156
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (self actions?)
					0
				else
					(curRoom setInset: inVatBook)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance underDesk of Feature
	(properties
		x 80
		y 144
		noun 24
		nsTop 136
		nsLeft 46
		nsBottom 147
		nsRight 115
		approachX 136
		approachY 143
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (self actions?)
					0
				else
					(curRoom setScript: sLookLasso)
				)
			)
			(8 (self doVerb: 1))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance intercom of Feature
	(properties
		x 54
		y 122
		noun 4
		nsTop 119
		nsLeft 47
		nsBottom 126
		nsRight 62
		sightAngle 40
	)
)

(instance mopBucket of Feature
	(properties
		x 238
		y 133
		noun 5
		nsTop 108
		nsLeft 231
		nsBottom 158
		nsRight 246
		sightAngle 40
	)
)

(instance bear of Feature
	(properties
		x 285
		y 146
		noun 6
		nsTop 104
		nsLeft 251
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(sFX number: 643 loop: 1 flags: 1 play:)
				(messager say: 6 4)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skeleton of Feature
	(properties
		x 199
		y 154
		noun 7
		nsTop 119
		nsLeft 166
		nsBottom 189
		nsRight 233
		sightAngle 40
	)
)

(instance brooms of Feature
	(properties
		x 8
		y 118
		noun 8
		nsTop 91
		nsBottom 146
		nsRight 16
		sightAngle 40
	)
)

(instance block of Feature
	(properties
		x 235
		y 172
		noun 9
		nsTop 156
		nsLeft 203
		nsBottom 189
		nsRight 268
		sightAngle 40
	)
)

(instance light of Feature
	(properties
		x 151
		y 80
		noun 11
		nsTop 74
		nsLeft 142
		nsBottom 86
		nsRight 160
		sightAngle 40
	)
)

(instance nautilus of Feature
	(properties
		x 140
		y 121
		noun 12
		nsTop 111
		nsLeft 133
		nsBottom 132
		nsRight 147
		sightAngle 40
	)
)

(instance nefertiti of Feature
	(properties
		x 196
		y 183
		z 100
		noun 21
		nsTop 73
		nsLeft 184
		nsBottom 93
		nsRight 208
		sightAngle 40
	)
)

(instance squirrel of Feature
	(properties
		x 299
		y 32
		noun 14
		nsTop 11
		nsLeft 279
		nsBottom 54
		nsRight 319
		sightAngle 40
	)
)

(instance heads of Feature
	(properties
		x 299
		y 81
		noun 15
		nsTop 56
		nsLeft 279
		nsBottom 107
		nsRight 319
		sightAngle 40
	)
)

(instance beam1 of Feature
	(properties
		x 138
		y 16
		noun 16
		nsTop 10
		nsBottom 22
		nsRight 276
		sightAngle 40
	)
)

(instance beam2 of Feature
	(properties
		x 142
		y 51
		noun 17
		nsTop 46
		nsLeft 49
		nsBottom 56
		nsRight 235
		sightAngle 40
	)
)

(instance leftStuff of Feature
	(properties
		x 114
		y 96
		noun 18
		nsTop 74
		nsLeft 87
		nsBottom 119
		nsRight 142
		sightAngle 40
	)
)

(instance rightStuff of Feature
	(properties
		x 184
		y 95
		noun 19
		nsTop 72
		nsLeft 159
		nsBottom 118
		nsRight 209
		sightAngle 40
	)
)

(instance rightmostStuff of Feature
	(properties
		x 246
		y 87
		noun 20
		nsTop 30
		nsLeft 210
		nsBottom 107
		nsRight 279
		sightAngle 40
	)
)

(instance blender of Feature
	(properties
		y 97
		noun 28
		nsTop 75
		nsLeft 97
		nsBottom 95
		nsRight 109
		sightAngle 40
	)
)

(instance ernieTimer of Timer
	(properties)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
