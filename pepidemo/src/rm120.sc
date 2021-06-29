;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include sci.sh)
(use Main)
(use CycleBet)
(use BalloonTalker)
(use TWInvItem)
(use TWRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm120 0
	specialTalker 1
	proc120_2 2
	flowerBed 3
	softGum 4
	obrienTalker 15
)

(local
	local0
	local1
	local2
	[local3 2] = [0 1]
	local5
	local6 =  1
	local7
	[local8 8]
	[local16 8] = [10 10 20 10 20 20 10 20]
	local24
	local25
	local26
	local27
	[local28 5] = [0 17 18 19 20]
	local33
	local34
	[local35 9] = [163 154 3 3 86 0 0 120]
)
(procedure (proc120_2 &tmp temp0 temp1 temp2 temp3)
	(= temp0 (+ ((ScriptID 895 1) x?) 23))
	(= temp1 (- ((ScriptID 895 1) x?) 23))
	(= temp2 (- ((ScriptID 895 1) y?) 11))
	(= temp3 (+ ((ScriptID 895 1) y?) 7))
	(= [local8 0] temp0)
	(= [local8 1] temp2)
	(= [local8 2] temp0)
	(= [local8 3] temp3)
	(= [local8 4] temp1)
	(= [local8 5] temp3)
	(= [local8 6] temp1)
	(= [local8 7] temp2)
	(if (not argc)
		(lockjawPoly points: @local8 size: 4)
	else
		(lockjawPoly points: @local16 size: 4)
	)
)

(instance rm120 of ADRoom
	(properties
		noun 22
		picture 120
		north 135
		vanishingY 20
	)
	
	(method (init)
		(super init: &rest)
		(LoadMany 128 121 132 807 122)
		(inventory
			addAfter:
				(inventory at: 5)
				(Hard_Gum init: yourself:)
				(Soft_Gum init: yourself:)
				(Drain_Pipe init: yourself:)
		)
		(if (Btst 4)
			(pipeFeature noun: 5)
			(Bset 3)
			(drainPipe
				init:
				cel: 3
				x: 88
				y: 67
				setPri: 5
				noun: 5
				approachX: 64
				approachY: 117
				approachVerbs: 85 7 6 86 30 89
				addToPic:
			)
		else
			(drainPipe
				init:
				approachVerbs: 85 7 6 86 30 89 10
				stopUpd:
			)
		)
		(if (Btst 6)
			(Bset 54)
			(if (not (Btst 4)) (ego get: 7))
		else
			(hardGum init:)
		)
		(mrsOWindow init: approachVerbs: 85 7 6 86 30 89)
		(house init:)
		(pipeFeature init: approachVerbs: 7 6 103)
		(porch init: setOnMeCheck: 1 8192)
		(flowerBed
			init:
			approachVerbs: 7 6 86 30
			setOnMeCheck: 1 256
		)
		(lockJawHouse
			init:
			approachVerbs: 85 7 6 86 30 89
			setOnMeCheck: 1 8
		)
		(appleTree
			init:
			approachVerbs: 85 7 6 86 30 89
			setOnMeCheck: 1 2
		)
		(obrienHouse init: approachVerbs: 7 6)
		(waterSpicket init: approachVerbs: 7 6 86)
		(waterDish init: approachVerbs: 85 7 6 86 30 89 10 9)
		(fence
			init:
			approachVerbs: 7 6 30 89 86 85
			setOnMeCheck: 1 128
		)
		(sideWalk
			init:
			approachVerbs: 85 7 6 86 30 89
			setOnMeCheck: 1 1024
		)
		(pepperWindow
			init:
			approachVerbs: 85 7 6 86 30 89
			setOnMeCheck: 2048
		)
		(foliage
			init:
			setOnMeCheck: 1 256
			approachVerbs: 85 7 6 86 30 89
		)
		(door init: approachVerbs: 7 6 30 86 85)
		(theMusic number: 120 flags: 1 setLoop: 1 play: self)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 122 125 155 125 181 141 181 150 139 160 111 160 85 153 85 142
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						319
						189
						319
						163
						263
						163
						263
						151
						319
						151
						319
						132
						249
						132
						249
						99
						203
						99
						196
						103
						196
						132
						181
						132
						181
						139
						154
						123
						115
						123
						115
						126
						64
						126
						64
						117
						44
						110
						0
						115
						0
						189
					yourself:
				)
		)
		(bird init: setScript: sBird approachVerbs: 7 6 85)
		(self setScript: initScr)
		(narrator y: 25 x: 20)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script)
			(
				(and
					(not (Btst 54))
					(InRect
						212
						139
						222
						151
						((ScriptID 895 0) x?)
						((ScriptID 895 0) y?)
					)
				)
				(curRoom setScript: gumWalkScr 0 0)
			)
		)
		(if (== (theMusic number?) 121)
			(cond 
				(
					(and
						(not local1)
						(== ((ScriptID 895 0) view?) 800)
						(== ((ScriptID 895 0) loop?) 8)
					)
					(theMusic send: 5 78 1)
					(= local1 1)
				)
				(
					(and
						local1
						(== ((ScriptID 895 0) view?) 800)
						(!= ((ScriptID 895 0) loop?) 8)
					)
					(theMusic send: 5 78 0)
					(= local1 0)
				)
				(
					(and
						(not local2)
						(== ((ScriptID 895 1) view?) 807)
						(== ((ScriptID 895 1) loop?) 8)
					)
					(theMusic send: 4 78 1)
					(= local2 1)
				)
				(
					(and
						local2
						(== ((ScriptID 895 1) view?) 807)
						(!= ((ScriptID 895 1) loop?) 8)
					)
					(theMusic send: 4 78 0)
					(= local2 0)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(inventory
			delete: Hard_Gum
			delete: Soft_Gum
			delete: Drain_Pipe
		)
		(super dispose:)
	)
	
	(method (cue)
		(theMusic number: 121 setLoop: -1 play:)
	)
	
	(method (newRoom n)
		(narrator x: -1 y: -1)
		(obrienTimer dispose: delete:)
		(birdTimer dispose: delete:)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance lockjawDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(9
				(ego put: 6 curRoomNum)
				(curRoom setScript: (ScriptID 121 3))
				(= local0 2)
			)
			(17
				(theGame points: 200 1)
				(curRoom setScript: sFinalClimb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pepperDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(9 (messager say: 18 9))
			(10 (messager say: 18 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egg of View
	(properties
		view 127
		loop 4
		priority 14
		signal $6810
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 6)
			(messager say: 2 6 4 2)
		else
			(bird doVerb: theVerb)
		)
	)
)

(instance bird of Prop
	(properties
		x 262
		y 161
		z 100
		noun 2
		approachX 260
		approachY 170
		view 127
		loop 2
		priority 14
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(++ local25)
				(cond 
					((== local26 1) (messager say: 2 6 4 1))
					((> local25 3) (messager say: 2 6 1 1))
					((== local25 3) (self setScript: sLayEgg))
					((== local25 2) (messager say: 2 6 3 1))
					(else (messager say: 2 6 2 1))
				)
			)
			(7
				(if (== local26 2)
					(messager say: 2 7 1)
				else
					(messager say: 2 7 2)
				)
			)
			(86 (messager say: 2 86))
			(30 (messager say: 2 30))
			(85
				(if (== local26 2)
					(specialTalker x: 100 y: 45 tailPosn: 4)
					(messager say: 2 85 2)
				else
					(specialTalker x: 107 y: 66 tailPosn: 4)
					(messager say: 2 85 1)
				)
			)
			(89
				(if (== local26 2)
					(messager say: 2 89 1)
				else
					(messager say: 2 89 2)
				)
			)
			(9 (messager say: 2 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(if (!= local26 2)
			(self setScript: sBird)
			(= local26 0)
		)
	)
)

(instance hardGum of Prop
	(properties
		x 215
		y 145
		noun 9
		approachX 217
		approachY 141
		_approachVerbs 33
		view 120
		cel 1
	)
	
	(method (init)
		(gumFeature init:)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (dispose)
		(gumFeature dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 9 6 15))
			(7
				(curRoom setScript: gumWalkScr self 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(ego get: 6)
	)
)

(instance softGum of View
	(properties
		x 215
		y 145
		noun 24
		approachX 255
		approachY 150
		view 120
		cel 1
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 24 6 15))
			(7
				(cond 
					((== local0 3) (pipeFeature doVerb: 7))
					((== local0 5) (drainPipe doVerb: 7))
					(else (curRoom setScript: sGetGum))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drainPipe of Prop
	(properties
		x 97
		y 119
		noun 21
		approachX 96
		approachY 125
		view 120
		cel 2
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(theGame points: 233 1)
				(cond 
					((not (Btst 4)) (curRoom setScript: sGetPipe))
					(
					(and (not (cast contains: hole)) (not (ego has: 0)))
						(theGame points: 204 1)
						(curRoom setScript: (ScriptID 121 4))
					)
					(else (messager say: 5 7 5))
				)
			)
			(10
				(if (& (inventory state?) $0020)
					(inventory curIcon: Drain_Pipe)
					(Drain_Pipe noun: 20 cel: 2)
					((ScriptID 894 2) init:)
					(inventory hide:)
					(= local0 4)
				else
					(softGum
						init:
						approachVerbs: 85 7 6 86 30 89
						setLoop: 0
						cel: 1
						x: 102
						y: 122
						approachX: 96
						approachY: 125
						setPri: 4
					)
					(= local0 5)
					(theGame points: 232 2)
				)
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0 stopUpd:)
				((ScriptID 895 0) forceUpd: stopUpd:)
				(ego put: 7)
				(Bset 3)
				(messager say: 26 10 0 1 self)
			)
			(6
				(if (not (Btst 4))
					(messager say: (self noun?) theVerb 15 0)
				else
					(messager say: 5 6)
				)
			)
			(9
				(if (& (inventory state?) $0020) (inventory hide:))
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0 stopUpd:)
				((ScriptID 895 0) stopUpd:)
				(messager say: 21 9 0 0 self)
			)
			(85
				(if (not (Btst 3))
					(messager say: noun 85)
				else
					(messager say: 26 102)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(theGame handsOn:)
		((ScriptID 895 1) normal: 1 startUpd:)
		((ScriptID 895 0) startUpd:)
		(super cue: &rest)
	)
)

(instance mrsOBrien of Prop
	(properties
		x 28
		y 20
		noun 13
		approachX 44
		approachY 110
		approachDist 20
		view 123
		loop 5
		priority 10
		signal $0010
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(85
				(theGame points: 203 1)
				(curRoom setScript: sTalkOBrien)
			)
			(6 (messager say: 13 6))
			(7 (messager say: 13 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue &tmp [temp0 2])
		(super cue: &rest)
		(switch (++ local24)
			(1
				(obrienTimer setReal: self 7)
			)
			(2
				(if (curRoom script?)
					(-- local24)
					(obrienTimer setReal: self 2)
				else
					(= local34 0)
					(self cel: 6 cycleSpeed: 4 setCycle: Beg self)
				)
			)
			(3
				(= local24 0)
				(= cycleSpeed 6)
				(self dispose:)
			)
			(100
				(theGame handsOff:)
				(= local34 0)
				(self cel: 6 cycleSpeed: 4 setCycle: Beg self)
			)
			(101
				(= local24 0)
				(theGame handsOn:)
				(= cycleSpeed 6)
				(self dispose:)
			)
		)
	)
)

(instance mrsOWindow of Feature
	(properties
		x 38
		y 100
		z 100
		noun 15
		nsTop 10
		nsLeft 8
		nsBottom 73
		nsRight 69
		sightAngle 40
		approachX 44
		approachY 110
		approachDist 220
	)
	
	(method (doVerb theVerb &tmp [temp0 900])
		(cond 
			((cast contains: mrsOBrien) (mrsOBrien doVerb: theVerb))
			((Message msgGET 120 15 theVerb 21 1 @temp0) (messager say: 15 theVerb 21))
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance pipeFeature of Feature
	(properties
		x 83
		y 56
		noun 26
		nsLeft 63
		nsBottom 113
		nsRight 103
		sightAngle 40
		approachX 83
		approachY 56
		approachDist 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(theGame points: 204 1)
				(if (Btst 4)
					(drainPipe doVerb: 7)
				else
					(messager say: 26 7)
				)
			)
			(10
				(ego put: 7)
				(Bset 3)
				(softGum
					init:
					view: 120
					cel: 1
					x: 84
					y: 65
					setPri: 4
					approachVerbs: 85 7 6 86 30 89
				)
				(= local0 3)
				(softGum approachX: 83 approachY: 56)
				(messager say: 26 10)
				(theGame points: 232 2)
			)
			(103
				(if (not (Btst 3))
					(curRoom setScript: installPipeScr 0 0)
				else
					(Bset 4)
					(= local0 6)
					(theGame points: 198 3)
					(curRoom setScript: installPipeScr 0 1)
				)
			)
			(9 (messager say: 26 9))
			(85
				(if (and (== local0 6) (Btst 3))
					(messager say: 26 102)
				else
					(messager say: 26 85)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance house of Feature
	(properties
		x 167
		y 1
		noun 16
		nsLeft 66
		nsBottom 126
		nsRight 268
		sightAngle 40
		approachX 167
		approachY 63
		_approachVerbs 32
	)
)

(instance porch of Feature
	(properties
		x 218
		y 108
		nsTop 93
		nsLeft 182
		nsBottom 124
		nsRight 255
		sightAngle 40
		approachX 201
		approachY 100
		_approachVerbs 33
	)
)

(instance flowerBed of Feature
	(properties
		x 32
		y 90
		noun 6
		nsTop 76
		nsBottom 104
		nsRight 64
		sightAngle 40
		approachDist 30
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= global215 50)
		(switch theVerb
			(7
				(if (== local34 1)
					(messager say: 6 7 12)
				else
					(curRoom setScript: sPickFlowers)
				)
			)
			(30
				(curRoom setScript: lockjawDigsScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lockJawHouse of Feature
	(properties
		x 134
		y 125
		noun 3
		approachX 150
		approachY 159
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 86)
			(curRoom setScript: (ScriptID 875 1) 0 @local35)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance appleTree of Feature
	(properties
		x 252
		y 2
		noun 1
		nsLeft 185
		nsBottom 163
		nsRight 319
		sightAngle 40
		approachX 277
		approachY 163
		approachDist 10
	)
)

(instance waterSpicket of Feature
	(properties
		x 72
		y 86
		noun 28
		nsTop 77
		nsLeft 62
		nsBottom 96
		nsRight 80
		sightAngle 40
		approachX 72
		approachY 86
		approachDist 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(curRoom setScript: addWaterScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance waterDish of Feature
	(properties
		x 67
		y 105
		noun 27
		nsTop 95
		nsLeft 55
		nsBottom 115
		nsRight 80
		sightAngle 40
		approachX 67
		approachY 105
		approachDist 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(9
				(if (not local7)
					(messager say: 27 9 23)
				else
					(messager say: 27 9 22)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fence of Feature
	(properties
		x 159
		y 161
		noun 4
		nsTop 133
		nsBottom 189
		nsRight 319
		sightAngle 40
		approachX 136
		approachY 187
	)
	
	(method (doVerb theVerb &tmp [temp0 50])
		(= global215 9)
		(switch theVerb
			(7 (messager say: 4 7))
			(6 (messager say: 4 6))
			(86 (messager say: 4 86))
			(30 (messager say: 4 30))
			(85 (messager say: 4 85))
			(89 (messager say: 4 89))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sideWalk of Feature
	(properties
		x 210
		y 142
		noun 23
		approachX 189
		approachY 170
	)
)

(instance pepperWindow of Feature
	(properties
		x 132
		y 52
		noun 19
		nsTop 14
		nsLeft 97
		nsBottom 90
		nsRight 168
		sightAngle 40
		approachX 130
		approachY 120
		approachDist 10
	)
	
	(method (doVerb theVerb)
		(if (not (OneOf theVerb 85 7 6 86 30 89))
			(super doVerb: theVerb &rest)
		else
			(messager say: 19 theVerb)
		)
	)
)

(instance foliage of Feature
	(properties
		x 249
		y 157
		noun 7
		nsTop 125
		nsLeft 180
		nsBottom 189
		nsRight 319
		sightAngle 40
		approachX 196
		approachY 174
		_approachVerbs 32
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(85
				(if (== (++ local33) 3)
					(specialTalker x: 160 y: 160 tailPosn: 1)
					(messager say: 7 85 14)
				else
					(messager say: 7 85)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Feature
	(properties
		x 225
		y 69
		noun 17
		nsTop 40
		nsLeft 194
		nsBottom 99
		nsRight 256
		sightAngle 40
		onMeCheck $0004
		approachX 203
		approachY 100
	)
	
	(method (doVerb theVerb)
		(= global215 102)
		(switch theVerb
			(1
				(curRoom setScript: sDoor 0 theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance obrienHouse of Feature
	(properties
		x 36
		y 41
		noun 14
		nsBottom 82
		nsRight 73
		sightAngle 40
		approachX 44
		approachY 110
		approachDist 10
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 85)
			(mrsOWindow doVerb: 85)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance Hard_Gum of TWInvItem
	(properties
		cel 3
		message 9
		noun 9
		modNum 120
		name "Hard Gum"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(103 (drainPipe doVerb: 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Soft_Gum of TWInvItem
	(properties
		cel 4
		message 10
		noun 24
		modNum 120
		name "Soft Gum"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(103
				(theGame points: 232 2)
				(drainPipe doVerb: 10)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Drain_Pipe of TWInvItem
	(properties
		cel 1
		message 103
		noun 21
		modNum 120
		name "Drain Pipe"
	)
	
	(method (init)
		(if (Btst 3) (= noun 20) (= cel 2))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(10
				(drainPipe doVerb: 10)
				(theGame points: 232 2)
			)
			(9 (drainPipe doVerb: 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gumFeature of Feature
	(properties
		x 215
		y 146
		noun 9
		nsTop 141
		nsLeft 208
		nsBottom 152
		nsRight 223
	)
	
	(method (doVerb theVerb)
		(hardGum doVerb: theVerb)
	)
)

(instance lockjawPoly of Polygon
	(properties
		type $0002
	)
)

(instance obrienTalker of BalloonTalker
	(properties
		x 41
		y 71
		talkWidth 150
		tailPosn 3
	)
	
	(method (dispose)
		(mrsOBrien setCycle: 0)
		(super dispose: &rest)
	)
	
	(method (say)
		((ScriptID 895 1) stopUpd:)
		((ScriptID 895 0) stopUpd:)
		(mrsOBrien setCycle: CycleBet 5 15 1)
		(super say: &rest)
	)
)

(instance specialTalker of BalloonTalker
	(properties
		talkWidth 150
	)
)

(instance obrienTimer of Timer
	(properties)
)

(instance birdTimer of Timer
	(properties)
)

(instance sTalkOBrien of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0 stopUpd:)
				(if (!= local34 1)
					(if (not (cast contains: mrsOBrien))
						(mrsOBrien init: approachVerbs: 85 7 6 86 30 89)
					)
					(mrsOBrien setCycle: CT 5 1 self)
				else
					(= cycles 2)
				)
			)
			(1
				(= local34 1)
				((ScriptID 895 0) setHeading: 0 self)
			)
			(2 (= cycles 2))
			(3
				(switch (++ local27)
					(1
						((ScriptID 2000 3) talkWidth: 165)
						(messager say: 13 85 [local28 local27] 1 3 self)
					)
					(2
						((ScriptID 2000 3) talkWidth: 165)
						(obrienTalker talkWidth: 80)
						(= state 99)
						(messager say: 13 85 [local28 local27] 0 self)
					)
					(3
						(= state 99)
						(messager say: 13 85 [local28 local27] 0 self)
					)
					(else 
						(= state 99)
						(messager say: 13 85 [local28 4] 0 self)
					)
				)
			)
			(4
				((ScriptID 2000 3) talkWidth: 0)
				(= state 99)
				(messager say: 13 85 [local28 local27] 4 5 self)
			)
			(100
				((ScriptID 895 1) normal: 1 startUpd:)
				((ScriptID 2000 3) talkWidth: 0)
				(obrienTalker talkWidth: 150)
				(= local24 99)
				(mrsOBrien cue:)
				(self dispose:)
			)
		)
	)
)

(instance sDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0)
				(if (== register 30)
					(self changeState: 20)
				else
					(messager say: 17 7 0 1 self)
				)
			)
			(1
				((ScriptID 895 1) normalize: loop: 8 cel: 5)
				(= ticks 30)
			)
			(2
				((ScriptID 895 1) setHeading: 0 self)
				(messager say: 17 7 0 2 self)
			)
			(3)
			(4 (ego setHeading: 180 self))
			(5 (= cycles 2))
			(6
				((ScriptID 2000 3) talkWidth: 175 winX: 20 winY: 65)
				(messager say: 17 7 0 3 self)
			)
			(7
				((ScriptID 2000 3) talkWidth: 0 winX: 0 winY: 0)
				((ScriptID 895 1) setHeading: 225 self)
				(messager say: 17 7 0 4 6 self)
			)
			(8)
			(9
				(theGame handsOn:)
				((ScriptID 895 1) normal: 1)
				(self dispose:)
			)
			(20
				(Face (ScriptID 895 0) (ScriptID 895 1) self)
			)
			(21
				(messager say: 17 30 0 0 self)
			)
			(22
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLayEgg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(bird setLoop: 3 cel: 0 cycleSpeed: 12 setCycle: 0)
				(= ticks 30)
			)
			(1
				(theMusic2 number: 1228 setLoop: 1 play:)
				(bird setCycle: End self)
			)
			(2 (= seconds 2))
			(3
				(egg x: (bird x?) y: (- (bird y?) 100) init: stopUpd:)
				(= local26 2)
				(= ticks 30)
			)
			(4 (messager say: 2 6 4 2 self))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPissOnTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 895 1)
					normalize:
					normal: 0
					setMotion: PolyPath 264 153 self
				)
			)
			(1
				((ScriptID 895 1) setLoop: 8 cel: 5)
				(= ticks 30)
			)
			(2
				(if (== local26 2)
					(egg setLoop: 4)
					(bird setPri: 15 y: 31 z: 0)
				)
				(bird setScript: 0 setLoop: 0 setCycle: Fwd)
				((ScriptID 895 1)
					view: 130
					setLoop: 2
					cel: 0
					x: 262
					y: 154
					setCycle: End self
				)
				(theMusic2 number: 1226 setLoop: 1 play:)
				(= local26 1)
			)
			(3 (= ticks 60))
			(4
				((ScriptID 895 1) setCycle: Beg self)
			)
			(5
				((ScriptID 895 1)
					normalize:
					loop: 8
					cel: 5
					x: 264
					y: 153
				)
				(= ticks 30)
			)
			(6
				((ScriptID 895 1) setMotion: PolyPath 58 150 self)
			)
			(7
				(birdTimer setReal: bird 5)
				(self dispose:)
			)
		)
	)
)

(instance sGetPipe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(!= ((ScriptID 895 0) x?) 83)
						(!= ((ScriptID 895 0) y?) 130)
					)
					(-- state)
					(ego setMotion: PolyPath 83 130 self)
				else
					(ego setHeading: 90 self)
				)
			)
			(1
				(ego
					view: 839
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(2 (= ticks 30))
			(3
				(ego get: 8 setCycle: Beg self)
				(if (== local0 5)
					(softGum dispose:)
					(= local0 4)
					(Drain_Pipe noun: 20 cel: 2)
				)
				(drainPipe dispose:)
			)
			(4
				(ego normalize: loop: 8 cel: 0)
				(= ticks 30)
			)
			(5
				(ego normalize: setHeading: 180 self)
			)
			(6 (= cycles 2))
			(7
				(messager say: 21 7 15 0 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetGum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(!= ((ScriptID 895 0) x?) 247)
						(!= ((ScriptID 895 0) y?) 156)
					)
					(-- state)
					(ego setMotion: PolyPath 247 156 self)
				else
					(ego setHeading: 90 self)
					(Bset 63)
				)
			)
			(1
				(ego
					view: 839
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(2 (= ticks 30))
			(3
				(softGum dispose:)
				(ego setCycle: Beg self)
			)
			(4
				(theGame points: 234 1)
				(ego normalize: loop: 8 cel: 0 get: 7)
				(= cycles 2)
			)
			(5
				(ego normalize: setHeading: 180 self)
			)
			(6 (= cycles 2))
			(7
				(messager say: 12 104 0 1 self)
			)
			(8
				(theGame handsOn:)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance sBird of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (not modelessDialog)
					(client setLoop: (Random 1 2) cel: 0 setCycle: End self)
				else
					(client stopUpd:)
					(= cycles 2)
				)
			)
			(1 (= ticks (Random 20 60)))
			(2
				(if (not modelessDialog)
					(client setCycle: Beg self)
				else
					(client stopUpd:)
					(= cycles 2)
				)
			)
			(3
				(= state -1)
				(= ticks (Random 30 120))
			)
		)
	)
)

(instance sPickFlowers of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (ego x?) 39) (!= (ego y?) 111))
					(-- state)
					(ego setMotion: PolyPath 39 111 self)
				else
					(theGame points: 202 1)
					(ego setLoop: 7 setMotion: MoveTo 32 109 self)
				)
			)
			(1
				(ego setLoop: 8 cel: 7)
				(= ticks 60)
			)
			(2
				(switch (++ local5)
					(1 (messager say: 6 7 7 1 self))
					(2 (messager say: 6 7 8 1 self))
					(3
						((ScriptID 2000 3) talkWidth: 160)
						(messager say: 6 7 9 1 self)
					)
					(4
						(messager say: 6 7 10 1 self)
					)
					(5
						(messager say: 6 7 11 1 self)
					)
					(else 
						(= state 6)
						(= register 1)
						(messager say: 6 7 6 1 self)
					)
				)
			)
			(3
				(mrsOBrien
					init:
					setCycle: CT 5 1 self
					approachVerbs: 85 7 6 86 30 89
				)
				(= local34 1)
				(ego view: 839 setLoop: 1 cel: 0 setCycle: End self)
			)
			(4)
			(5
				(switch local5
					(1 (messager say: 6 7 7 2 self))
					(2 (messager say: 6 7 8 2 self))
					(3 (messager say: 6 7 9 2 self))
					(4
						(messager say: 6 7 10 2 self)
					)
					(5
						(messager say: 6 7 11 2 self)
					)
					(else  (= cycles 2))
				)
			)
			(6
				(mrsOBrien setCycle: 0 stopUpd:)
				(ego setCycle: Beg self)
			)
			(7
				(ego normalize: setHeading: 125 self)
			)
			(8
				(ego setMotion: MoveTo 39 111 self)
			)
			(9
				(theGame handsOn:)
				(if (== local5 5) (= local24 99))
				(if (not register) (mrsOBrien cue:))
				(self dispose:)
			)
		)
	)
)

(instance initScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					init:
					normalize:
					setScale: 0
					loop: 2
					cel: 0
					posn: 199 117
					ignoreActors: 1
					actions: pepperDoVerb
				)
				((ScriptID 895 1)
					init:
					normalize:
					setScale: 0
					actions: lockjawDoVerb
					approachVerbs: 6 7 85
					loop: 2
					cel: 4
					posn: 222 116
					noun: 12
					approachX: 201
					approachY: 132
					ignoreActors: 1
				)
				(= cycles 2)
			)
			(1
				(proc120_2)
				(curRoom addObstacle: lockjawPoly)
				((ScriptID 895 1) normal: 1)
				(= cycles 2)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance addWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc120_2 1)
				(Face ego (ScriptID 895 1) self)
			)
			(1 (= cycles 2))
			(2
				(if local7
					(messager say: 28 7 24 1 self)
				else
					(messager say: 28 7 0 1 self)
				)
			)
			(3
				((ScriptID 895 0)
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 49 113 self
				)
			)
			(4
				(Load rsVIEW 130)
				((ScriptID 895 0) loop: 8 cel: 6)
				(= ticks 30)
			)
			(5
				((ScriptID 895 0)
					view: 130
					x: 52
					cycleSpeed: 8
					setLoop: 0
					cel: 0
					setCycle: CT 2 1 self
				)
				(theGame points: 194 1)
			)
			(6
				(theMusic2 number: 1226 setLoop: 1 play:)
				((ScriptID 895 0) setCycle: End self)
			)
			(7
				(theMusic2 stop:)
				(= ticks 30)
			)
			(8
				((ScriptID 895 0) normalize: loop: 8 cel: 6 x: 49)
				(= ticks 30)
			)
			(9
				((ScriptID 895 1) normal: 0)
				((ScriptID 895 0) setMotion: PolyPath 13 118 self)
			)
			(10
				((ScriptID 895 0) setHeading: 90)
				((ScriptID 895 1)
					normalize:
					ignoreActors: 1
					setMotion: PolyPath 52 104 self
				)
			)
			(11
				(theMusic2 number: 904 setLoop: -1 play:)
				((ScriptID 895 1)
					view: 130
					x: 54
					setLoop: 1
					cel: 0
					setPri: 1
					setCycle: Fwd
					cycleSpeed: 6
				)
				(= seconds (Random 4 6))
			)
			(12
				((ScriptID 895 1) normalize: x: 52 loop: 8 cel: 4)
				(theMusic2 stop:)
				(= ticks 30)
			)
			(13
				(if (== (++ local7) 2)
					(self setScript: sPissOnTree self)
				else
					((ScriptID 895 1)
						setLoop: 2
						cycleSpeed: 1
						moveSpeed: 1
						setMotion: MoveTo 58 150 self
					)
				)
			)
			(14
				((ScriptID 895 1) normal: 1 approachX: 59 approachY: 157)
				(proc120_2)
				((ScriptID 895 0) normalize:)
				(= cycles 2)
			)
			(15
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance gumWalkScr of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(== ((ScriptID 895 0) view?) 121)
					(== ((ScriptID 895 0) loop?) 0)
					(not (theMusic2 handle?))
					(or
						(== ((ScriptID 895 0) cel?) 1)
						(== ((ScriptID 895 0) cel?) 8)
					)
				)
				(theMusic2 number: 1201 loop: 1 play:)
			)
			(
				(and
					(== ((ScriptID 895 0) view?) 121)
					(== ((ScriptID 895 0) loop?) 1)
					(== ((ScriptID 895 0) cel?) 3)
					(not (theMusic2 handle?))
				)
				(theMusic2 number: 1202 loop: 1 play:)
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0)
				(if register (theGame points: 195 1) (Bset 54))
				(if
				(and (!= (ego x?) 217) (!= (ego y?) 141) register)
					(-- state)
					(ego setMotion: PolyPath 217 141 self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 3))
			(2
				((ScriptID 895 0)
					view: 121
					posn: 224 147
					setLoop: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(3
				(messager say: 9 7 15 1 self)
			)
			(4
				((ScriptID 895 0) setCycle: Beg self)
			)
			(5
				(if register
					(theGame points: 195 1)
					(hardGum dispose:)
					((ScriptID 895 0) setLoop: 1 setCycle: End self)
				else
					(= cycles 2)
				)
			)
			(6
				((ScriptID 895 0)
					normalize:
					loop: 8
					cel: 2
					heading: 180
					x: (- ((ScriptID 895 0) x?) 4)
				)
				((ScriptID 2000 3)
					talkWidth: 70
					winX: 228
					winY: 109
					tailPosn: 0
					forceWidth: 1
				)
				(messager say: 9 7 15 2 self)
			)
			(7
				((ScriptID 2000 3)
					talkWidth: 0
					winX: 0
					winY: 0
					forceWidth: 0
				)
				(if (not register)
					((ScriptID 895 0)
						cycleSpeed: 3
						setMotion:
							PolyPath
							((ScriptID 895 0) x?)
							(+ ((ScriptID 895 0) y?) 10)
							self
					)
				else
					((ScriptID 895 1) normal: 1)
					(= cycles 1)
				)
			)
			(8
				(theGame handsOn:)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance installPipeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 122
					posn: 83 79
					setLoop: 0
					cel: 0
					cycleSpeed: 5
					put: 8
					setCycle: CT 4 1 self
				)
			)
			(1
				(theMusic2 number: 1207 loop: 1 play: self)
			)
			(2
				(ego cel: 3 setCycle: End self)
			)
			(3
				(theMusic2 loop: -1)
				(ego normalize: loop: 8 cel: 6 x: 68 y: 117 heading: 45)
				(switch register
					(0
						(drainPipe
							init:
							view: 132
							setLoop: 6
							cel: 0
							x: 72
							y: 105
							setPri: 5
							setCycle: CT 4 1 self
						)
					)
					(1
						(drainPipe init: cel: 3 posn: 88 67 setPri: 5 addToPic:)
						(if (cast contains: softGum) (softGum dispose:))
						(= ticks 30)
					)
				)
			)
			(4
				(if (not register)
					(theMusic2 number: 1214 setLoop: 1 play:)
					(drainPipe setCycle: CT 10 1 self)
				else
					(self cue:)
				)
			)
			(5
				(if (not register)
					(theMusic2 number: 1214 setLoop: 1 play:)
					(drainPipe setCycle: End self)
				else
					(self cue:)
				)
			)
			(6
				(if (not register)
					(drainPipe
						view: 120
						loop: 0
						cel: 2
						x: 97
						y: 119
						setPri: 4
						forceUpd:
						stopUpd:
					)
				)
				(ego setCycle: Rev setMotion: MoveTo 57 121 self)
			)
			(7
				(if
				(and (OneOf local0 4 3 5) (cast contains: softGum))
					(softGum dispose:)
				)
				(ego normalize: setHeading: 180 self)
			)
			(8 (= cycles 2))
			(9
				(if register
					(messager say: 26 102 0 1 self)
				else
					(messager say: 26 103 0 1 self)
				)
			)
			(10
				(pipeFeature noun: 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance hole of View
	(properties
		x 35
		y 102
		noun 11
		approachX 52
		approachY 111
		view 120
		priority 1
		signal $6810
	)
	
	(method (doVerb theVerb)
		(if (not (ego has: 0)) (= noun 10) else (= noun 11))
		(switch theVerb
			(7
				(curRoom setScript: sGetHarness)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sGetHarness of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (ego has: 0)
					(messager say: 11 7 0 0 self)
				else
					(++ state)
					(ego view: 839 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
			(2
				(hole setLoop: 1 cel: 0)
				(ego get: 0 setCycle: Beg self)
				(messager say: 10 7 0 0 self)
			)
			(3)
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lockjawDigsScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame points: 199 2)
				(Bset 64)
				((ScriptID 895 0) setHeading: 225)
				((ScriptID 895 1) normal: 0 normalize: setScript: 0)
				(if
					(and
						(!= ((ScriptID 895 1) x?) 17)
						(!= ((ScriptID 895 1) y?) 116)
					)
					(-- state)
					((ScriptID 895 1) setMotion: PolyPath 17 116 self)
				else
					(= ticks 30)
				)
			)
			(1
				((ScriptID 895 1) setLoop: 8 cel: 3)
				(= ticks 30)
			)
			(2
				(messager say: 6 30 13 1 self)
			)
			(3
				((ScriptID 895 0) setMotion: MoveTo 52 118 self)
			)
			(4
				((ScriptID 895 0)
					setPri: -1
					setLoop: 4
					setMotion: MoveTo 73 158 self
				)
			)
			(5
				((ScriptID 895 1)
					normalize:
					setLoop: 6
					setMotion: MoveTo 36 108 self
				)
				((ScriptID 895 0) setLoop: -1 setHeading: 310 self)
			)
			(6)
			(7
				((ScriptID 895 1) setLoop: 8 cel: 7)
				(= ticks 30)
			)
			(8
				(= register 2)
				((ScriptID 895 1)
					view: 123
					setLoop: 3
					posn: 31 109
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
				(if (not (theMusic2 handle?))
					(theMusic2 number: 903 setLoop: -1 play:)
				)
			)
			(9
				((ScriptID 895 1) setCycle: CT 3 -1 self)
			)
			(10
				((ScriptID 895 1) cel: 3 setCycle: End self)
			)
			(11
				(if (not (-- register))
					((ScriptID 895 1)
						setCycle: 0
						normalize:
						loop: 8
						cel: 7
						posn: 36 108
					)
					(hole init: approachVerbs: 85 7 6 86 30 89 stopUpd:)
					(theMusic2 stop:)
					(mrsOBrien
						init:
						setCycle: CT 5 1 self
						approachVerbs: 85 7 6 86 30 89
					)
					(= local34 1)
				else
					(= state (- state 2))
					(= cycles 2)
				)
			)
			(12
				(messager say: 6 30 13 2 self)
			)
			(13
				(mrsOBrien setCycle: 0 stopUpd:)
				(messager say: 6 30 13 3 self)
			)
			(14
				(mrsOBrien setCycle: 0 stopUpd:)
				((ScriptID 895 0) setHeading: 180)
				((ScriptID 895 1)
					normalize:
					ignoreActors: 1
					illegalBits: 0
					setLoop: 4
					setMotion: MoveTo 52 149 self
				)
			)
			(15
				((ScriptID 895 1)
					setLoop: 2
					setMotion: MoveTo 53 159 self
				)
			)
			(16
				((ScriptID 895 1)
					view: 838
					setLoop: 1
					cel: 2
					setCycle: 0
				)
				(= ticks 60)
			)
			(17
				((ScriptID 895 1) hide:)
				((ScriptID 895 0)
					view: 124
					setLoop: 0
					cel: 0
					x: 62
					y: 158
				)
				(= ticks 30)
			)
			(18
				((ScriptID 895 0) cycleSpeed: 4 setCycle: End self)
			)
			(19
				(messager say: 6 30 13 4 self)
			)
			(20
				((ScriptID 895 0) setCycle: Beg self)
			)
			(21
				((ScriptID 895 0) normalize: loop: 8 cel: 2 x: 73 y: 158)
				((ScriptID 895 1)
					view: 838
					cel: 2
					setLoop: 1
					setCycle: 0
					show:
				)
				(= ticks 30)
			)
			(22
				(messager say: 6 30 13 5 6 self)
			)
			(23 (= cycles 2))
			(24
				(messager say: 6 30 13 7 self)
			)
			(25
				((ScriptID 895 0)
					ignoreActors: 1
					setMotion: MoveTo 91 170 self
				)
			)
			(26
				(proc120_2)
				((ScriptID 895 1)
					normal: 1
					approachX: 49
					approachY: 169
					setScript: (ScriptID 838 0)
				)
				(= local24 99)
				(mrsOBrien cue:)
				(theGame setEgo: (ScriptID 895 0))
				(self dispose:)
			)
		)
	)
)

(instance sFinalClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0 setScript: 0)
				((ScriptID 895 0)
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 66 121 self
				)
			)
			(1
				((ScriptID 895 0) setMotion: MoveTo 66 112 self)
			)
			(2
				(Face (ScriptID 895 0) (ScriptID 895 1) self)
			)
			(3 (= cycles 2))
			(4
				(messager say: 12 107 16 1 4 self)
			)
			(5
				((ScriptID 895 1)
					normalize:
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 32 115 self
				)
			)
			(6
				((ScriptID 895 1)
					setLoop: 8
					cel: 6
					setCycle: 0
					x: 32
					y: 115
				)
				(= ticks 30)
			)
			(7
				((ScriptID 895 0) setLoop: 8 cel: 6 setCycle: 0)
				(= ticks 30)
			)
			(8
				((ScriptID 895 1) hide:)
				((ScriptID 895 0)
					view: 132
					cycleSpeed: 8
					setLoop: 3
					cel: 0
					x: 17
					y: 115
				)
				(theMusic fade:)
				(= ticks 30)
			)
			(9
				(ego
					setLoop: 5
					cel: 0
					x: 55
					y: 54
					setPri: 6
					cycleSpeed: 12
					setCycle: CT 2 1 self
				)
				(theMusic number: 122 setLoop: 1 flags: 1 play:)
			)
			(10
				(theGame points: 235 2)
				((ScriptID 2000 3) winX: 67 winY: 93 tailPosn: 3)
				((ScriptID 2004 0) winX: 39 winY: 123 tailPosn: 3)
				(messager say: 12 107 16 5 7 self)
			)
			(11
				((ScriptID 895 0) setCycle: End self)
			)
			(12
				((ScriptID 895 1) setScript: (ScriptID 838 0))
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)
