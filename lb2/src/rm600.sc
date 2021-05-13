;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use n027)
(use MuseumRgn)
(use Inset)
(use Scaler)
(use PolyPath)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
	northDoor 1
	eastDoor 2
)

(local
	local0
	local1
	local2
	local3
)
(instance rm600 of LBRoom
	(properties
		noun 6
		picture 600
		north 650
		east 610
		south 530
		vanishingY 40
	)
	
	(method (init)
		(ego init: normalize: 831 setScale: Scaler 102 0 190 40)
		(self setRegions: 90)
		(switch prevRoomNum
			(521
				(theGame handsOff:)
				(ego posn: 160 170)
				(self setScript: sOlympiaEnters)
			)
			(north
				(ego edgeHit: 0 setHeading: 180)
				(theMusic2 stop:)
			)
			(south
				(ego x: 160)
				(theMusic2 fade:)
				(theGame handsOn:)
			)
			(east
				(ego edgeHit: 0 setHeading: 270)
				(eastDoor caller: self)
				(theMusic2 stop:)
			)
			(else 
				(ego wearingGown: 1 posn: 160 170)
				(theGame handsOn:)
			)
		)
		(WrapMusic pause: 0)
		(super init:)
		(if (Btst 10)
			(glass approachVerbs: 4 1 setCel: (glass lastCel:) init:)
			(if (not (ego has: 15))
				(lantern
					approachVerbs: 1 4
					setPri: 14
					y: (+ (lantern y?) 50)
					z: 50
					init:
				)
			)
		else
			(glass approachVerbs: 4 1 init:)
			(lantern approachVerbs: 1 4 init:)
			(Load rsSOUND 600)
		)
		(northDoor approachVerbs: 4 init:)
		(doorbell approachVerbs: 4 init:)
		(eastDoor approachVerbs: 4 init:)
		(columnA init:)
		(columnB init:)
		(columnC init:)
		(columnD init:)
		(archA init:)
		(archB init:)
		(ceiling init:)
		(southExit init:)
		(if (not (Btst 16))
			(Load rsSOUND 637)
			(ferret init:)
			(ferretTimer setReal: ferret (Random 15 45))
		else
			(Bclr 16)
		)
		(if (and (Btst 12) (not (Btst 61)))
			(Load rsVIEW 635)
			(Load rsSOUND 636)
			(meatTimer setReal: bugsWithMeat 5)
		)
		(if
			(and
				(== currentAct 4)
				(not (== prevRoomNum 521))
				(not (Btst 62))
				(TriggerEvent 16648 1)
				(not (Btst 92))
			)
			(Bset 62)
			(self setScript: sMeanwhile)
		)
		(if
			(and
				(not (Btst 84))
				(== ((ScriptID 90 2) room?) curRoomNum)
			)
			((ScriptID 90 2) goTo: 650)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script 0)
			((IsObjectOnControl ego 2) (self setScript: sExitToStairs))
		)
	)
	
	(method (dispose)
		(ferretTimer dispose: delete:)
		(meatTimer dispose: delete:)
		(if local0 (Bclr 10))
		(WrapMusic pause: 1)
		(super dispose:)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(if
			(and
				(or (== newRoomNum north) (== newRoomNum south))
				(not (Btst 4))
			)
			(cond 
				((not (TriggerEvent -20222)) 0)
				((not (TriggerEvent 4880)) (Bclr 18))
				(else 0)
			)
		)
		(if (!= newRoomNum north) (Bclr 117))
	)
)

(instance sExitToStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sBreakGlass of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 55 188 self)
			)
			(1
				(if (== register 29)
					(= temp0 48)
					(= temp1 175)
				else
					(= temp0 41)
					(= temp1 176)
				)
				(ego setMotion: MoveTo temp0 temp1 self)
			)
			(2
				(ego
					view: 601
					setScale: Scaler 100 100 190 40
					setLoop: (if (== register 29) 1 else 2)
					setCel: 0
					setPri: 15
					setCycle: End self
				)
			)
			(3
				(sFX number: 600 flags: 1 loop: 1 play:)
				(glass setCycle: CT 3 1 self)
			)
			(4
				(lantern setPri: 14 y: (+ (lantern y?) 50) z: 50)
				(glass setCycle: End self)
			)
			(5
				(Bset 10)
				(glass addToPic:)
				(ego
					normalize: 831
					setScale: Scaler 102 0 190 40
					setMotion: MoveTo 59 187 self
				)
			)
			(6 (ego setHeading: 270 self))
			(7
				(cond 
					((Btst 109) (theGame handsOn:) (self dispose:))
					((and (Btst 108) (not (Random 0 2)))
						(Load rsVIEW 814)
						(Load rsSOUND 19)
						(Bset 109)
						(self setScript: sTakeAwayLantern)
					)
					((and (not (Btst 108)) (Random 0 1))
						(Load rsVIEW 814)
						(Load rsSOUND 19)
						(Bset 108)
						(self setScript: sTakeAwayLantern)
					)
					(else (theGame handsOn:) (self dispose:))
				)
			)
		)
	)
)

(instance sTakeAwayLantern of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (!= prevRoomNum 650) (Btst 83))
					(self setScript: sHeimlichFromOffice self)
				else
					((ScriptID 32 0)
						init:
						setScale: Scaler 120 0 190 0
						view: 814
						room: curRoomNum
						posn: 197 250
						setMotion: PolyPath 79 186 self
					)
					(WrapMusic pause: 1)
					(theMusic2 number: 19 flags: 1 loop: -1 play:)
				)
			)
			(1 (ego setHeading: 90 self))
			(2 (= cycles 3))
			(3 (messager say: 7 0 0 0 self))
			(4
				(ego setMotion: PolyPath (ego x?) 240 self)
			)
			(5
				(= local0 1)
				(theMusic2 fade:)
				(curRoom newRoom: (curRoom south?))
				(self dispose:)
			)
		)
	)
)

(instance sHeimlichFromOffice of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(northDoor locked: 1)
				((ScriptID 32 0)
					init:
					setScale: 170
					view: 814
					room: curRoomNum
					setPri: 6
					setHeading: 180
					posn: (northDoor moveToX?) (northDoor moveToY?)
				)
				(WrapMusic pause: 1)
				(theMusic2 number: 19 flags: 1 loop: -1 play:)
				(northDoor setCycle: End self)
				(sFX number: 46 flags: 1 loop: 1 play:)
				(altPolyList delete: (northDoor doorPoly?))
			)
			(2
				((ScriptID 32 0)
					setPri: -1
					setMotion: PolyPath (northDoor approachX?) (northDoor approachY?) self
				)
			)
			(3
				(northDoor setCycle: Beg self)
				(sFX number: 47 flags: 1 loop: 1 play:)
				(altPolyList add: (northDoor doorPoly?))
			)
			(4
				((ScriptID 32 0) setMotion: PolyPath 79 186 self)
			)
			(5
				((ScriptID 32 0) setHeading: 270 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance sFerretRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ferret
					setLoop: 5
					setCycle: Walk
					setMotion: PolyPath 243 173 self
				)
				(sFXFerret play:)
			)
			(1
				(if (eastDoor cel?)
					(ferret
						setMotion: PolyPath (eastDoor moveToX?) (eastDoor moveToY?) self
					)
				else
					(ferret setMotion: PolyPath 347 210 self)
				)
			)
			(2
				(ferret dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFerretLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (eastDoor cel?)
					(ferret
						posn: (eastDoor moveToX?) (eastDoor moveToY?)
						setLoop: 4
						setCycle: Walk
						setMotion: PolyPath 243 173 self
					)
				else
					(ferret
						posn: 347 210
						setLoop: 4
						setCycle: Walk
						setMotion: PolyPath 243 173 self
					)
				)
				(sFXFerret play:)
			)
			(1
				(ferret setMotion: PolyPath 21 210 self)
			)
			(2
				(ferret dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sDoMeat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sFXBeetles play: 0 fade: 127 25 10 0 self)
			)
			(1
				(sFX number: 46 flags: 1 play:)
				(eastDoor setCycle: End self)
				(altPolyList delete: (eastDoor doorPoly?))
			)
			(2
				(bugsWithMeat
					init:
					posn: (eastDoor moveToX?) (eastDoor moveToY?)
					setLoop: 0
					setCycle: Walk
					setMotion: PolyPath (eastDoor approachX?) (eastDoor approachY?) self
				)
			)
			(3
				(sFX number: 47 flags: 1 play:)
				(eastDoor setCycle: Beg self)
				(altPolyList add: (eastDoor doorPoly?))
			)
			(4
				(theGame handsOn:)
				(bugsWithMeat setMotion: PolyPath 243 173 self)
			)
			(5
				(bugsWithMeat setMotion: PolyPath 21 210 self)
			)
			(6
				(sFXBeetles fade:)
				(Bset 61)
				(bugsWithMeat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sOlympiaEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(olympia
					posn: 100 210
					setCycle: Walk
					setScale: Scaler 102 0 190 40
					setPri: -1
					setMotion: PolyPath (- (ego x?) 20) (ego y?) self
					init:
				)
				(= seconds 2)
			)
			(2 (Face ego olympia))
			(3 (Face ego olympia self))
			(4
				(messager say: 1 0 24 0 self 1892)
			)
			(5
				(olympia setMotion: PolyPath 285 176 self)
			)
			(6
				(eastDoor setCycle: End)
				(altPolyList delete: (eastDoor doorPoly?))
				(olympia setMotion: PolyPath 314 157 self)
			)
			(7
				(altPolyList add: (eastDoor doorPoly?))
				(eastDoor setCycle: Beg self)
			)
			(8
				(theGame handsOn:)
				(olympia dispose:)
				(Bclr 62)
				(self dispose:)
			)
		)
	)
)

(instance ferret of Actor
	(properties
		x 21
		y 210
		view 632
		loop 5
		signal $4000
		cycleSpeed 4
		xStep 4
		moveSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(259
						(if (proc27_0 12 global298)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 4 0 0 1894)
							(proc27_1 12 @global298)
						)
					)
					(260
						(if (proc27_0 12 global299)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 5 0 0 1894)
							(proc27_1 12 @global299)
						)
					)
					(265
						(if (proc27_0 12 global304)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 10 0 0 1894)
							(proc27_1 12 @global304)
						)
					)
					(266
						(if (proc27_0 12 global305)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 11 0 0 1894)
							(proc27_1 12 @global305)
						)
					)
					(270
						(if (proc27_0 12 global309)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 15 0 0 1894)
							(proc27_1 12 @global309)
						)
					)
					(781
						(if (proc27_0 12 global333)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 39 0 0 1894)
							(proc27_1 12 @global333)
						)
					)
					(785
						(if (proc27_0 12 global337)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 43 0 0 1894)
							(proc27_1 12 @global337)
						)
					)
					(800
						(if (proc27_0 12 global352)
							(messager say: 1 6 1 0 0 1894)
						else
							(messager say: 1 6 58 0 0 1894)
							(proc27_1 12 @global352)
						)
					)
					(else 
						(messager say: 1 6 81 0 0 1894)
					)
				)
			)
			(41
				(messager say: 1 41 0 0 0 1894)
			)
			(8
				(messager say: 1 8 0 0 0 1894)
			)
			(19
				(messager say: 1 19 0 0 0 1894)
			)
			(24
				(messager say: 1 24 0 0 0 1894)
			)
			(25
				(messager say: 1 25 0 0 0 1894)
			)
			(23
				(messager say: 1 23 0 0 0 1894)
			)
			(2
				(messager say: 1 2 0 0 0 1894)
			)
			(4
				(messager say: 1 4 0 0 0 1894)
			)
			(1
				(messager say: 1 1 0 0 0 1894)
			)
			(else 
				(messager say: 1 0 0 0 0 1894)
			)
		)
	)
	
	(method (cue)
		(Bset 16)
		(if (Random 0 1)
			(self setScript: sFerretLeft)
		else
			(self setScript: sFerretRight)
		)
	)
)

(instance ferretTimer of Timer
	(properties)
)

(instance bugsWithMeat of Actor
	(properties
		noun 12
		view 635
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 12 2))
			(else 
				(if (not (OneOf theVerb 3 1 4 2))
					(messager say: 12)
				else
					(super doVerb: theVerb)
				)
			)
		)
	)
	
	(method (cue)
		(if (curRoom script?)
			(meatTimer setReal: self 5)
		else
			(self setScript: sDoMeat)
		)
	)
)

(instance meatTimer of Timer
	(properties)
)

(instance lantern of View
	(properties
		x 22
		y 150
		approachX 55
		approachY 188
		view 600
		priority 13
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(lantern dispose:)
				(ego get: 15)
				((ScriptID 21 0) doit: 784)
			)
			(1
				(curRoom setInset: inLantern)
			)
			(8
				(curRoom setInset: inLantern)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance olympia of Actor
	(properties
		x 256
		y 184
		view 820
		cel 5
		priority 13
		signal $4010
	)
)

(instance glass of Prop
	(properties
		x 17
		y 159
		noun 5
		approachX 55
		approachY 188
		view 601
		priority 13
		signal $4011
		cycleSpeed 9
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (Btst 10)
						(messager say: 5 1 5)
					else
						(messager say: 5 1 4)
					)
				)
				(8
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 8 4)
					)
				)
				(4
					(if (Btst 10)
						(messager say: 5 4 5)
					else
						(messager say: 5 4 4)
					)
				)
				(29
					(cond 
						((Btst 10) (messager say: 5 29 5))
						((MuseumRgn nobodyAround:) (curRoom setScript: sBreakGlass 0 29))
						(else (return 1))
					)
				)
				(23
					(cond 
						((Btst 10) (super doVerb: theVerb))
						((MuseumRgn nobodyAround:) (curRoom setScript: sBreakGlass 0 23))
						(else (return 1))
					)
				)
				(22
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 22 4)
					)
				)
				(41
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 41 4)
					)
				)
				(19
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 19 4)
					)
				)
				(18
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 18 4)
					)
				)
				(21
					(if (Btst 10)
						(super doVerb: theVerb)
					else
						(messager say: 5 21 4)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance northDoor of LbDoor
	(properties
		x 152
		y 109
		noun 8
		approachX 134
		approachY 174
		view 600
		loop 1
		entranceTo 650
		listenVerb 38
		moveToX 134
		moveToY 155
		enterType 0
		exitType 0
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 4 38)
	)
	
	(method (listen)
		(cond 
			(
			(or (Btst 84) (== prevRoomNum (curRoom north?)) local3) (super listen: &rest))
			((Btst 83)
				(messager say: 1 38 4 0 0 1600)
				(northDoor locked: 1)
				(Bset 84)
			)
			((Btst 82)
				(messager say: 1 38 3 0 0 1600)
				(northDoor locked: 1)
				(Bset 83)
				(= local3 1)
			)
			((Btst 81)
				(messager say: 1 38 2 0 0 1600)
				(northDoor locked: 1)
				(Bset 82)
				(= local3 1)
			)
			(else
				(messager say: 1 38 1 0 0 1600)
				(northDoor locked: 1)
				(Bset 81)
			)
		)
	)
	
	(method (createPoly)
		(super
			createPoly: 109 161 109 149 161 149 161 161 112 161
		)
	)
)

(instance eastDoor of LbDoor
	(properties
		x 290
		y 96
		noun 9
		approachX 269
		approachY 175
		view 600
		loop 2
		entranceTo 610
		moveToX 310
		moveToY 158
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super createPoly: 285 152 306 157 305 164 282 159)
	)
)

(instance inLantern of Inset
	(properties
		view 600
		cel 1
		y 128
		disposeNotOnMe 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 19 1 0 0 0 15)
			)
			(8
				(messager say: 19 8 0 0 0 15)
			)
			(4
				(lantern dispose:)
				(ego get: 15)
				((ScriptID 21 0) doit: 784)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doorbell of Feature
	(properties
		x 112
		y 138
		noun 2
		nsTop 133
		nsLeft 109
		nsBottom 142
		nsRight 116
		sightAngle 40
		approachX 115
		approachY 170
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (messager say: 2 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance columnA of Feature
	(properties
		x 99
		y 116
		noun 1
		nsTop 67
		nsLeft 89
		nsBottom 165
		nsRight 109
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch local1
					(0 (messager say: 1 1 1))
					(1 (messager say: 1 1 2))
					(else  (messager say: 1 1 3))
				)
				(++ local1)
			)
			(4 (messager say: 1 4))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance columnB of Feature
	(properties
		x 179
		y 116
		noun 1
		nsTop 72
		nsLeft 169
		nsBottom 161
		nsRight 189
		sightAngle 40
	)
	
	(method (doVerb)
		(columnA doVerb: &rest)
	)
)

(instance columnC of Feature
	(properties
		x 234
		y 179
		noun 1
		nsLeft 217
		nsBottom 189
		nsRight 251
		sightAngle 40
	)
	
	(method (doVerb)
		(columnA doVerb: &rest)
	)
)

(instance columnD of Feature
	(properties
		x 312
		y 101
		noun 1
		nsTop 25
		nsLeft 306
		nsBottom 177
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb)
		(columnA doVerb: &rest)
	)
)

(instance archA of Feature
	(properties
		x 134
		y 96
		noun 10
		nsTop 87
		nsLeft 114
		nsBottom 105
		nsRight 154
		sightAngle 40
	)
)

(instance archB of Feature
	(properties
		x 293
		y 86
		noun 10
		nsTop 79
		nsLeft 288
		nsBottom 93
		nsRight 299
		sightAngle 40
	)
)

(instance ceiling of Feature
	(properties
		x 136
		y 28
		noun 3
		nsLeft 57
		nsBottom 56
		nsRight 216
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(switch local2
					(0 (messager say: 3 1 1))
					(else  (messager say: 3 1 2))
				)
				(++ local2)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 185
		nsLeft 46
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 4
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance sFXBeetles of Sound
	(properties
		flags $0001
		number 636
		loop -1
	)
)

(instance sFXFerret of Sound
	(properties
		flags $0001
		number 637
	)
)

(instance sMeanwhile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch prevRoomNum
					((curRoom north?)
						(ego edgeHit: 0 setHeading: 180)
						(theMusic2 stop:)
					)
					((curRoom south?) (ego x: 160))
					((curRoom east?)
						(ego edgeHit: 0 setHeading: 270)
						(eastDoor caller: self)
						(theMusic2 stop:)
					)
					(else 
						(ego wearingGown: 1 posn: 160 170)
					)
				)
				(= cycles 1)
			)
			(1 (curRoom newRoom: 521))
		)
	)
)
