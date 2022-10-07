;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
(use Main)
(use rgCrown)
(use walkEgoInScr)
(use KQ6Print)
(use KQ6Room)
(use Kq6Procs)
(use Print)
(use Inset)
(use PAvoid)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm240 0
)

(local
	thePshopDoor
	[local1 58] = [0 -10 319 -10 326 175 243 150 231 149 204 140 181 134 162 137 158 132 152 130 142 132 135 123 119 117 105 102 71 102 58 97 58 0 46 0 46 94 13 103 29 113 13 119 24 132 14 144 42 160 64 174 101 178 117 189 0 189]
	[local59 58] = [0 -10 319 -10 326 175 243 150 231 149 204 140 181 134 162 137 158 132 152 130 142 132 135 123 119 117 105 102 71 102 58 97 58 0 46 0 46 94 11 104 19 117 54 117 34 137 18 137 39 159 64 174 101 178 117 189 0 189]
)
(procedure (localproc_131a param1)
	(if param1
		(curRoom
			addObstacle: (roomPoly points: @local59 yourself:)
		)
	else
		(curRoom
			addObstacle: (roomPoly points: @local1 yourself:)
		)
	)
)

(instance rm240 of KQ6Room
	(properties
		noun 3
		picture 240
		horizon 10
		south 210
	)
	
	(method (init)
		(LoadMany 128 240 241)
		(Load rsMESSAGE picture)
		(super init: &rest)
		(ego
			init:
			reset: 3
			setAvoider: PAvoider
			setScale: Scaler 90 72 188 95
		)
		(if (> currentAct 2)
			(proclamation init:)
		else
			(procCover init: stopUpd:)
		)
		(pot init:)
		(doorBell1 init:)
		(doorBell2 init:)
		(genericFeatures init:)
		(randomVillagers init: setScript: villagersScr)
		(if ((ScriptID 10 0) isSet: 2)
			((ScriptID 10 0) clrIt: 2)
			(clown init:)
		)
		(cond 
			((OneOf prevRoomNum 280 270)
				(if (== prevRoomNum 280)
					(pshopDoor cel: 5)
				else
					(bshopDoor cel: 5)
				)
				(self setScript: enterFromShopScr)
			)
			((== prevRoomNum 250) (self setScript: enterFromVillage2Scr))
			(else (proc12_1 191 185 -45))
		)
		(pshopDoor init:)
		(bshopDoor init:)
		(bush1 init:)
		(bush2 init:)
		(if (>= (theGame _detailLevel?) 2)
			(bush1 setScript: (Clone (ScriptID 13 0)))
			(bush2 setScript: (ScriptID 13 0))
		)
		(if (not ((ScriptID 10 0) isSet: 512))
			(theMusic number: 240 loop: -1 play:)
		else
			((ScriptID 10 0) clrIt: 512)
		)
		(if
			(and
				(not (OneOf prevRoomNum 270 280))
				(ego has: 0)
				(not (Btst 110))
			)
			(Bset 110)
			(proc10_2 dumpTrashScr)
		)
		(if (and (OneOf currentAct 1 5) (not (Btst 12)))
			((ScriptID 241 0) init:)
			(localproc_131a 1)
		else
			(localproc_131a 0)
		)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000)
				(theMusic fade: 0 10 10 0)
				(self setScript: exitToVillage2Scr)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (OneOf newRoomNum 270 280)
			(theMusic fade: 70 10 15 0)
		)
		(DisposeScript 11)
		(DisposeScript 923)
		(DisposeScript 927)
		(DisposeScript 964)
		(DisposeScript 930)
		(DisposeScript 13)
		(DisposeScript 241)
		(super dispose:)
	)
	
	(method (newRoom n)
		(if (and (== n 210) (cast contains: clown))
			((ScriptID 10 0) setIt: 2)
		)
		(if (and (Btst 53) (not (== n 270)))
			(Bset 54)
			(Bclr 53)
		)
		(super newRoom: n &rest)
	)
	
	(method (notify)
		((curRoom obstacles?) delete: roomPoly)
		(localproc_131a 0)
	)
	
	(method (edgeToRoom param1)
		(return
			(if (== param1 3)
				(theMusic fade: 0 10 10 0)
				(proc12_0 param1 -45)
				(return 0)
			else
				(super edgeToRoom: param1 &rest)
			)
		)
	)
)

(instance procInset of Inset
	(properties
		view 242
		x 80
		y 73
		disposeNotOnMe 1
		noun 10
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOn:)
		(theIconBar disable: 0 1 3 4 5 6)
	)
	
	(method (doVerb)
		(Print
			font: userFont
			posn: 160 30
			width: 138
			addText: 10 1 0 1
			init:
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (not (= temp0 (super onMe: event &rest)))
			(theGame handsOn:)
			(theIconBar enable: 6)
		)
		(return temp0)
	)
)

(instance exitToVillage2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setScale: Scaler 75 25 95 68
					setMotion: DPath 43 92 33 90 self
				)
			)
			(1 (curRoom newRoom: 250))
		)
	)
)

(instance enterFromVillage2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 34 90
					setScale: Scaler 75 25 95 68
					setMotion: DPath 43 92 54 96 self
				)
			)
			(1
				(ego
					setScale: Scaler 90 72 188 95
					setMotion: MoveTo 55 101 self
				)
			)
			(2
				(if (not script) (= cycles 1))
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitToShopScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable:)
				(ego
					setMotion: 0
					setSpeed: 6
					view: 241
					loop: 1
					cel: 0
					normal: 0
				)
				(if (== register 1)
					(ego posn: 232 144 scaleX: 117 scaleY: 117)
					(= thePshopDoor pshopDoor)
				else
					(ego posn: 160 130 scaleX: 107 scaleY: 107)
					(= thePshopDoor bshopDoor)
				)
				(ego setScale: setCycle: EndLoop self)
			)
			(1
				(if (== register 1)
					(ego posn: 239 128)
				else
					(ego setPri: 9 posn: 171 115)
				)
				(ego loop: 0 cel: 0 setCycle: EndLoop self)
				(soundFx2 number: 901 loop: 1 play:)
				(thePshopDoor cel: 0 setCycle: CycleTo 4 1 self)
			)
			(2 0)
			(3
				(ego reset:)
				(theIconBar enable:)
				(if (== (self register?) 1)
					(curRoom newRoom: 280)
				else
					(curRoom newRoom: 270)
				)
			)
		)
	)
)

(instance enterFromShopScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0 view: 241 loop: 2 cel: 0 normal: 0)
				(if (== prevRoomNum 280)
					(ego posn: 224 145 scaleX: 117 scaleY: 117)
					(= thePshopDoor pshopDoor)
				else
					(ego setPri: 9 posn: 158 129 scaleX: 107 scaleY: 107)
					(= thePshopDoor bshopDoor)
				)
				(ego setScale:)
				(= cycles 2)
			)
			(1
				(thePshopDoor setCycle: BegLoop self)
			)
			(2 (= cycles 2))
			(3
				(thePshopDoor stopUpd:)
				(soundFx2 number: 902 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(4
				(if (not (cast contains: clown)) (theGame handsOn:))
				(ego reset: 5 setScale: Scaler 90 72 188 95)
				(if (== prevRoomNum 280)
					(ego posn: 214 147)
				else
					(ego setPri: -1 posn: 150 132)
				)
				(self dispose:)
			)
		)
	)
)

(instance dumpTrashScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Bset 110) (= seconds 2))
			(1
				(soundFx2 number: 901 loop: 1 play:)
				(pshopDoor
					view: 248
					cycleSpeed: 9
					loop: 1
					cel: 0
					setCycle: CycleTo 6 1 self
				)
			)
			(2
				(soundFx number: 241 loop: 1 play:)
				(pshopDoor setCycle: EndLoop self)
			)
			(3
				(soundFx2 number: 902 loop: 1 play:)
				(= cycles 2)
			)
			(4
				(pshopDoor view: 240 loop: 0 cel: 0 stopUpd:)
				(= cycles 2)
			)
			(5 (self dispose:))
		)
	)
)

(instance searchUrnScr of Script
	(properties)
	
	(method (dispose)
		(DisposeScript 231)
		(super dispose:)
		(= register 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					normal: 0
					posn: 194 135
					view: 248
					loop: 0
					cel: 0
					setScale: 0
				)
				(= cycles 2)
			)
			(1 (ego setCycle: CycleTo 2 1 self))
			(2
				(soundFx number: 241 loop: -1 play:)
				(ego setCycle: (ScriptID 231 0) 3)
				(= ticks 120)
			)
			(3
				(soundFx stop:)
				(messager
					say:
						26
						5
						(cond 
							((and (Btst 110) (not (Btst 111))) (= register 1) (ego get: 51) (Bset 111) 38)
							((Btst 110) 39)
							(else 37)
						)
						0
						self
				)
			)
			(4 (ego setCycle: BegLoop self))
			(5 (= cycles 2))
			(6
				(ego
					reset: 0
					posn: (pot approachX?) (pot approachY?)
					setScale: Scaler 90 72 188 95
				)
				(= cycles 2)
			)
			(7
				(if register (theGame givePoints: 1))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance villagersScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client hide:)
				(= state
					(switch (Random 0 2)
						(0 0)
						(1 8)
						(2 17)
					))
				(= seconds (Random 45 60))
			)
			(1
				(client
					show:
					loop: 1
					cel: 0
					posn: 159 123 78
					setCycle: EndLoop self
				)
			)
			(2
				(client loop: 0 cel: 0)
				(= ticks 60)
			)
			(3
				(client loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4 (= ticks 60))
			(5
				(client loop: 3 setCycle: BegLoop self)
			)
			(6 (= ticks 60))
			(7
				(client loop: 3 setCycle: EndLoop self)
			)
			(8
				(client loop: 2 cel: 0 setCycle: EndLoop self)
				(= state -1)
			)
			(9
				(client
					show:
					loop: 5
					cel: 0
					posn: 198 123 90
					setCycle: EndLoop self
				)
			)
			(10
				(client loop: 4 cel: 0)
				(= ticks (Random 45 120))
			)
			(11
				(client loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(12 (= ticks (Random 45 120)))
			(13
				(client loop: 6 cel: 0 setCycle: BegLoop self)
			)
			(14 (= ticks (Random 45 120)))
			(15
				(client loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(16 (= ticks (Random 45 120)))
			(17
				(client loop: 7 cel: 0 setCycle: EndLoop self)
				(= state -1)
			)
			(18
				(client
					show:
					posn: 247 122 84
					loop: 9
					cel: 0
					setCycle: EndLoop self
				)
			)
			(19
				(client loop: 8 cel: 0)
				(= ticks (Random 45 120))
			)
			(20
				(client loop: 12 cel: 0 setCycle: EndLoop self)
			)
			(21 (= ticks (Random 45 120)))
			(22 (client setCycle: BegLoop self))
			(23 (= ticks (Random 45 120)))
			(24
				(client loop: 10 cel: 0 setCycle: EndLoop self)
			)
			(25
				(client loop: 11 cel: 0 setCycle: Forward)
				(= ticks 80)
			)
			(26
				(client loop: 13 cel: 0 setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance clownScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: PolyPath 156 250 self)
			)
			(1
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(= seconds 20)
			)
			(2 (client dispose:))
		)
	)
)

(instance lookAtProcScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 0)
				(if (not (Bset 71))
					(if (or (Btst 72) (Btst 52))
						(= register 30)
					else
						(= register 31)
					)
					(messager say: 8 1 30 1 self)
				else
					(messager say: 8 1 33 1 self)
					(= register 33)
				)
			)
			(1
				(ego
					setMotion:
						PolyPath
						(proclamation approachX?)
						(proclamation approachY?)
						self
				)
			)
			(2 (ego setHeading: 0 self))
			(3 (= cycles 2))
			(4
				(procInset init: 0 curRoom)
				(= cycles 2)
			)
			(5
				(if (& msgType $0002)
					(messager say: 10 1 0 1 self)
				else
					(KQ6Print
						font: userFont
						posn: 160 30
						width: 138
						addText: 10 1 0 1
						init: self
					)
				)
			)
			(6 (= cycles 2))
			(7
				(cond 
					((and (& msgType $0002) (!= register 33))
						(messager
							say: 8 1 register (if (== register 30) 2 else 1) self
						)
					)
					((!= register 33)
						(KQ6Print
							font: userFont
							posn: 160 52
							width: 138
							addText: 8 1 register (if (== register 30) 2 else 1)
							init: self
						)
					)
					(else (= cycles 1))
				)
			)
			(8 (self dispose:))
		)
	)
)

(instance bshopDoor of Prop
	(properties
		x 186
		y 134
		z 49
		noun 46
		approachX 155
		approachY 131
		view 240
		loop 1
		priority 5
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(curRoom setScript: exitToShopScr 0 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pshopDoor of Prop
	(properties
		x 255
		y 99
		noun 45
		approachX 218
		approachY 147
		view 240
		signal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: exitToShopScr 0 1)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(else 
				(bshopDoor doVerb: theVerb &rest)
			)
		)
	)
)

(instance randomVillagers of Prop
	(properties
		noun 33
		view 246
	)
	
	(method (doVerb theVerb)
		(if (== (approachCode doit: theVerb) -32768)
			(= theVerb 5)
		)
		(if (== theVerb 2)
			(messager say: noun theVerb (Random 27 28))
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance clown of Actor
	(properties
		x 139
		y 140
		view 717
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self
			setScale: Scaler 90 72 188 95
			setCycle: Walk
			setSpeed: 4
			setScript: clownScr
		)
	)
)

(instance procCover of Prop
	(properties
		x 90
		y 86
		noun 50
		view 2401
		priority 6
		signal $0010
	)
)

(instance bush1 of Prop
	(properties
		x 11
		y 167
		view 240
		loop 2
		priority 15
		signal $4010
		cycleSpeed 2
	)
)

(instance bush2 of Prop
	(properties
		x 54
		y 186
		view 240
		loop 3
		cel 3
		priority 15
		signal $4010
		cycleSpeed 4
	)
)

(instance proclamation of Feature
	(properties
		x 90
		y 101
		noun 8
		nsTop 75
		nsLeft 83
		nsBottom 91
		nsRight 96
		sightAngle 40
		approachX 88
		approachY 109
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 5)
			(curRoom setScript: lookAtProcScr)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pot of Feature
	(properties
		x 213
		y 135
		noun 26
		onMeCheck $0100
		approachX 195
		approachY 134
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: searchUrnScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return
			(if
				(and
					(== (OnControl 4 (event x?) (event y?)) onMeCheck)
					(< (event x?) 235)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 5) (OneOf noun 11 26 24)) (messager say: 24 theVerb))
			(
				(or
					(and
						(or
							(OneOf theVerb 5 2)
							(== (approachCode doit: theVerb) -32768)
						)
						(== noun 14)
						(= noun 13)
					)
					(and (== theVerb 2) (== noun 40) (= noun 41))
					(and
						(== (approachCode doit: theVerb) -32768)
						(or
							(and (== noun 44) (= noun 42))
							(and (== noun 49) (= theVerb 5))
						)
					)
				)
				(super doVerb: theVerb &rest)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (onMe event)
		(= x (event x?))
		(= y (event y?))
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(2
					(if (< (event y?) 100) (= y 154) 5 else 0)
				)
				(4096 (= y 131) 47)
				(8192 (= y 131) 48)
				(8 (= y 97) 50)
				(32 (= y 122) 43)
				(16 (= y 122) 13)
				(64 (= y 134) 41)
				(128 (= y 120) 42)
				(512
					(if (> (event x?) 96) (= y 154) 23 else 49)
				)
				(256
					(cond 
						((> (event x?) 265) (= y 155) 11)
						((< (event x?) 235) 0)
						(else (= y 146) 24)
					)
				)
				(1024
					(cond 
						((> (event y?) 131) (= y 160) 25)
						((< (event y?) 80) (= y 148) 40)
						(else (= y 151) 44)
					)
				)
				(2048 (= y 153) 14)
				(else  0)
			)
		)
	)
)

(instance doorBell1 of Feature
	(properties
		x 185
		y 132
		z 56
		noun 12
		nsTop 70
		nsLeft 179
		nsBottom 82
		nsRight 191
		sightAngle 40
	)
)

(instance doorBell2 of Feature
	(properties
		x 225
		y 139
		z 53
		noun 12
		nsTop 81
		nsLeft 221
		nsBottom 91
		nsRight 229
		sightAngle 40
	)
)

(instance roomPoly of Polygon
	(properties
		size 29
		type $0002
	)
)
