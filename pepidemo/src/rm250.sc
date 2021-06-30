;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use ExitFeature)
(use OccasionalCycle)
(use BalloonTalker)
(use TWInvItem)
(use TWRoom)
(use PAvoid)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm250 0
	watchManTalker 1
)

(local
	local0
	local1
	local2
)
(instance rm250 of ADRoom
	(properties
		noun 22
		picture 250
		north 210
		west 240
	)
	
	(method (init)
		(Load rsSCRIPT 927)
		(self
			addObstacle:
				(roomPoly
					type: 3
					init:
						208
						85
						149
						135
						113
						135
						113
						137
						143
						137
						143
						166
						91
						166
						91
						176
						297
						176
						297
						167
						266
						146
						215
						146
						215
						143
						263
						143
						239
						126
						188
						126
					yourself:
				)
		)
		(super init: &rest)
		(theMusic number: 250 setLoop: -1 play:)
		(ego
			init:
			normalize:
			setScale: 0
			actions: pepperActions
			setAvoider: PAvoider
		)
		(theGame handsOff:)
		(hole init:)
		(bone init:)
		(Bclr 10)
		(if (not (Btst 11)) (babyClothes init: stopUpd:))
		(if (not (Btst 10)) (boyClothes init: stopUpd:))
		((ScriptID 895 0)
			view: 800
			x: 231
			y: 131
			setHeading: 190
			ignoreActors: 0
		)
		((ScriptID 895 1)
			init:
			actions: lockjawActions
			x: 247
			y: 145
			setAvoider: PAvoider
			ignoreActors: 0
			setHeading: 225
		)
		(inventory
			addAfter:
				(inventory at: 5)
				(Bone init: yourself:)
				(Boy_s_Clothes init: yourself:)
				(Baby_s_Clothes init: yourself:)
		)
		(boyClothesFeature init:)
		(babyClothesFeature init:)
		(watchMan init: setScale: 100 setScript: sWatchMan)
		(self
			addObstacle:
				(lockjawPoly
					type: 2
					init: 239 150 239 164 195 164 195 150
					yourself:
				)
		)
		(roomTimer setReal: self 3)
		(northEFeature init:)
		(barrel init: setOnMeCheck: 1 16384)
		(blueApron init:)
		(clothesLine init: setOnMeCheck: 1 8192)
		(crate1 init: setOnMeCheck: 1 1024)
		(crate2 init: setOnMeCheck: 1 512)
		(crate3 init:)
		(crate4 init: setOnMeCheck: 1 256)
		(crate5 init: setOnMeCheck: 1 128)
		(crate6 init:)
		(crate8 init:)
		(girlSuit init:)
		(leaves init: setOnMeCheck: 1 4096)
		(pebbles1 init: approachVerbs: 7 setOnMeCheck: 1 2048)
		(pinkCoat init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((< (ego y?) 110)
				(if (or (not (Btst 10)) (not (Btst 11)))
					(theGame handsOff:)
					(ego setMotion: 0 y: (+ (ego y?) 2))
					(watchMan
						view: 810
						setMotion: 0
						setLoop: 0
						setScript: sHalt
					)
				else
					(curRoom setScript: sExitTo210)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(inventory
			delete: Bone
			delete: Boy_s_Clothes
			delete: Baby_s_Clothes
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(= global215 16)
		(super doVerb: theVerb)
	)
	
	(method (cue)
		(if
			(and
				local2
				(or
					(not (ego has: 6))
					(not (ego has: 7))
					(not (ego has: 8))
					(not (Btst 10))
				)
			)
			(messager say: 23 0 14)
		else
			(= local2 1)
			(roomTimer setReal: self 120)
			(theGame handsOn:)
		)
	)
	
	(method (newRoom n)
		(roomTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance pickUpPebbles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 19 7 0 0 self)
			)
			(1
				((ScriptID 895 0)
					view: (if (Btst 10) 805 else 839)
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 895 0) setCycle: BegLoop self)
			)
			(3
				((ScriptID 895 0) get: 4 normalize:)
				(theGame points: 230 2 handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBoyClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face (ScriptID 895 0) (ScriptID 895 1))
				(= seconds 2)
			)
			(1
				(messager say: 6 25 0 1 self)
			)
			(2
				(ego setMotion: PolyPath 154 131 self)
			)
			(3
				((ScriptID 895 0)
					view: 251
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
				((ScriptID 895 1)
					normalize:
					normal: 0
					ignoreActors: 1
					setMotion: MoveTo 183 120 self
				)
			)
			(4
				(bone
					view: 251
					setLoop: 2
					setCycle: Forward
					x: ((ScriptID 895 0) x?)
					y: (- ((ScriptID 895 0) y?) 10)
					init:
					setPri: (- ((ScriptID 895 0) priority?) 1)
					ignoreActors: 1
					setStep: 9 8
					setMotion: MoveTo 180 55 self
				)
			)
			(5)
			(6
				(bone dispose:)
				((ScriptID 895 0) normalize: setHeading: 90)
				((ScriptID 895 1)
					view: 251
					setLoop: 4
					setCel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(7
				(theMusic2 number: 2502 setLoop: 1 play:)
				(boyClothes hide:)
				((ScriptID 895 1) setCycle: EndLoop self)
			)
			(8
				(theGame points: 227 2)
				(= local1 1)
				(boyClothes show: setCel: 1 x: 183 y: 136 z: 30)
				((ScriptID 895 1)
					normalize:
					x: 179
					y: 123
					setMotion: MoveTo 232 153 self
				)
			)
			(9
				(sFx number: 2503 setLoop: 1 setVol: 127 play: self)
			)
			(10
				(Face (ScriptID 895 0) (ScriptID 895 1))
				(= seconds 3)
			)
			(11
				(messager say: 6 25 0 2 self)
			)
			(12
				(lockjawPoly dispose:)
				(if (or (ego has: 8) (Btst 11) local0)
					(ego put: 6)
					(messager say: 23 0 12 0 self)
				else
					(self cue:)
				)
			)
			(13
				(lockjawPoly
					type: 2
					init: 256 145 256 160 210 160 210 145
					yourself:
				)
				(theGame handsOn:)
				(boyClothesFeature dispose:)
				((ScriptID 895 1) normal: 1)
				(self dispose:)
			)
		)
	)
)

(instance sGetBabyClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face (ScriptID 895 0) (ScriptID 895 1))
				(= seconds 2)
			)
			(1
				(messager say: 2 25 0 1 self)
			)
			(2
				(ego setMotion: PolyPath 168 137 self)
			)
			(3
				((ScriptID 895 0)
					view: 251
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
				((ScriptID 895 1)
					normalize:
					normal: 0
					setMotion: MoveTo 240 148 self
				)
			)
			(4
				(bone
					view: 251
					setLoop: 2
					setCycle: Forward
					x: ((ScriptID 895 0) x?)
					y: (- ((ScriptID 895 0) y?) 10)
					init:
					setPri: (- ((ScriptID 895 0) priority?) 1)
					ignoreActors: 1
					setStep: 9 8
					setMotion: MoveTo 239 58 self
				)
			)
			(5)
			(6
				(bone dispose:)
				((ScriptID 895 0) normalize: setHeading: 90)
				((ScriptID 895 1)
					view: 251
					setLoop: 5
					setCel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(7
				(theMusic2 number: 2502 setLoop: 1 setVol: 127 play: self)
				(babyClothes hide:)
				((ScriptID 895 1) setCycle: EndLoop self)
			)
			(8)
			(9
				(sFx number: 2503 setLoop: 1 setVol: 127 play: self)
			)
			(10
				(theGame points: 226 2)
				(= local0 1)
				((ScriptID 895 1)
					normalize:
					setMotion: MoveTo 232 153 self
				)
				(babyClothes
					setCel: 3
					setPri: 4
					x: 225
					y: 140
					z: 30
					show:
				)
			)
			(11
				(Face (ScriptID 895 0) (ScriptID 895 1))
				(= seconds 2)
			)
			(12
				(lockjawPoly dispose:)
				(messager say: 2 25 0 2 self)
			)
			(13
				(lockjawPoly
					type: 2
					init: 256 145 256 160 210 160 210 145
					yourself:
				)
				(babyClothesFeature dispose:)
				(if (or (ego has: 7) (Btst 10) local1)
					(messager say: 23 0 12)
					(ego put: 6)
				)
				((ScriptID 895 1) normal: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWrapDog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 199 145 self)
			)
			(1
				((ScriptID 895 0) setHeading: 135)
				((ScriptID 895 1)
					normalize:
					normal: 0
					setMotion: PolyPath 232 153 self
				)
			)
			(2
				(messager say: 18 121 9 1 3 self)
			)
			(3
				(ego view: 252 setLoop: 2 setCel: 0 setCycle: EndLoop self)
				((ScriptID 895 1) hide:)
			)
			(4
				((ScriptID 2004 0)
					winX: 90
					winY: 131
					tailPosn: 1
					talkWidth: 100
				)
				(messager say: 18 121 9 4 5 self)
			)
			(5
				(theGame points: 229 2)
				(Bset 11)
				(ego
					put: 8
					setLoop: 5
					setCycle: Forward
					setMotion: MoveTo 222 146 self
				)
				(theIconBar advanceCurIcon:)
			)
			(6
				(theMusic fade:)
				(curRoom newRoom: 212)
			)
		)
	)
)

(instance sChangeClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 20 120 0 0 self)
			)
			(1
				(theGame points: 228 2)
				(ego setMotion: PolyPath 114 137 self)
			)
			(2
				(if (Btst 10)
					(Bclr 10)
					(ego view: 800)
				else
					(Bset 10)
					(ego view: 790)
				)
				(ego put: 7 setMotion: MoveTo 164 139 self)
			)
			(3
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(roomTimer dispose:)
				(ego ignoreActors: 1 setMotion: PolyPath 198 152 self)
				(theGame points: 224 1)
			)
			(1
				(bone hide:)
				((ScriptID 895 1)
					normalize:
					view: 250
					setLoop: 1
					normal: 0
					setCycle: OccasionalCycle self 1 10 20
				)
				(ego setHeading: 90 self)
			)
			(2 (= cycles 2))
			(3 (messager say: 5 7 2 1 self))
			(4
				(theMusic2 number: 905 setLoop: 1 play: self)
			)
			(5 (messager say: 5 7 2 2 self))
			(6
				(theMusic2 number: 1206 setLoop: 1 play: self)
			)
			(7
				((ScriptID 895 1) normalize: normal: 0)
				(bone show:)
				(messager say: 5 7 2 3 4 self)
			)
			(8
				((ScriptID 895 0)
					view: 839
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(9
				(bone dispose:)
				(roomPoly dispose:)
				((ScriptID 895 0) setCycle: BegLoop self)
			)
			(10
				((ScriptID 895 0)
					get: 6
					normalize:
					setMotion: MoveTo 162 146 self
				)
				((ScriptID 895 1)
					normalize:
					setMotion: MoveTo 215 157 self
				)
			)
			(11)
			(12
				(roomPoly
					type: 3
					init:
						208
						85
						149
						135
						113
						135
						113
						137
						143
						137
						143
						166
						91
						166
						91
						176
						297
						176
						297
						167
						239
						126
						188
						126
					yourself:
				)
				((ScriptID 895 0) setHeading: 90)
				((ScriptID 895 1) normal: 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFrom240 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 241 145 self)
			)
			(1
				((ScriptID 895 0) normalize: ignoreActors: 0)
				((ScriptID 895 1) ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitTo210 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setScale: Scaler 100 68 108 75
					setMotion: MoveTo 205 90 self
				)
			)
			(1
				(theMusic fade:)
				(if (== gameAct 1)
					(curRoom newRoom: 212)
				else
					(curRoom newRoom: 210)
				)
			)
		)
	)
)

(instance sHalt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				(if (< (client x?) 204)
					(client setLoop: 2)
				else
					(client setLoop: 3)
				)
				(client
					view: 815
					setCycle: Forward
					setMotion: MoveTo 204 92 self
				)
			)
			(2
				(client view: 810 setCycle: 0 setLoop: 4 stopUpd:)
				((ScriptID 895 0) stopUpd:)
				(= ticks 30)
			)
			(3
				(if (not (Btst 10))
					(messager say: 23 0 8 0 self)
				else
					(messager say: 23 0 7 0 self)
				)
			)
			(4
				(ego setHeading: 200 self)
				(client
					view: 815
					setLoop: 3
					setCycle: Forward
					setMotion: MoveTo 151 92
				)
			)
			(5
				(= global199 2)
				(theMusic fade:)
				(curRoom newRoom: 610)
			)
		)
	)
)

(instance sWatchMan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 0 5))
				(= seconds (Random 3 6))
			)
			(1
				(if (== (client loop?) 3)
					(client
						setCycle: Forward
						setLoop: 2
						setMotion: MoveTo 263 92 self
					)
				else
					(client
						setCycle: Forward
						setLoop: 3
						setMotion: MoveTo 151 92 self
					)
				)
			)
			(2 (= seconds 2))
			(3
				(if (== (client loop?) 2)
					(client setLoop: 3 setMotion: MoveTo 151 92 self)
				else
					(client setLoop: 2 setMotion: MoveTo 263 92 self)
				)
			)
			(4 (self init:))
		)
	)
)

(instance jumpForClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch register
					(1
						((ScriptID 895 0) setMotion: PolyPath 255 139 self)
					)
					(2
						((ScriptID 895 0) setMotion: PolyPath 144 138 self)
					)
					(3
						((ScriptID 895 0) setMotion: PolyPath 170 136 self)
					)
					(4
						((ScriptID 895 0) setMotion: PolyPath 242 134 self)
					)
				)
			)
			(1
				((ScriptID 895 0)
					view: 250
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 895 0) normalize:)
				(switch register
					(1 (messager say: 4 7 0 0 self))
					(2
						(messager say: 21 7 0 0 self)
					)
					(3 (messager say: 6 7 1 0 self))
					(4 (messager say: 2 7 1 0 self))
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boneOnClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 0) setMotion: PolyPath 255 139 self)
			)
			(1
				(Face (ScriptID 895 0) (ScriptID 895 1) self)
			)
			(2
				(messager say: 4 25 0 1 self)
			)
			(3
				((ScriptID 895 0)
					view: 251
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
				((ScriptID 895 1)
					normalize:
					setCycle: Walk
					ignoreActors: 1
					normal: 0
					setMotion: PolyPath 251 146 self
				)
			)
			(4
				(bone
					view: 251
					setLoop: 2
					setCycle: Forward
					x: ((ScriptID 895 0) x?)
					y: (- ((ScriptID 895 0) y?) 14)
					init:
					setPri: (- ((ScriptID 895 0) priority?) 1)
					ignoreActors: 1
					setStep: 9 8
					setMotion: MoveTo 261 58 self
				)
			)
			(5)
			(6
				(bone dispose:)
				((ScriptID 895 0) normalize:)
				((ScriptID 895 1)
					view: 251
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				((ScriptID 895 1) normalize:)
				(messager say: 4 25 0 2 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ljGetBone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: points: 225 2)
				((ScriptID 895 1) normal: 0)
				((ScriptID 895 0) setMotion: PolyPath 177 162 self)
			)
			(1
				(messager say: 18 25 0 1 self)
			)
			(2
				((ScriptID 895 0)
					view: 251
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 895 1)
					view: 251
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: 18 25 0 2 self)
			)
			(5
				((ScriptID 895 0) normalize: setHeading: 0)
				((ScriptID 895 1) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance pickupClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(messager say: 6 7 2 0 self)
				else
					(messager say: 2 7 2 0 self)
				)
			)
			(1
				(if register
					((ScriptID 895 0) setMotion: PolyPath 161 130 self)
				else
					((ScriptID 895 0) setMotion: PolyPath 217 126 self)
				)
			)
			(2
				((ScriptID 895 0)
					view: (if (not (Btst 10)) 839 else 805)
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((ScriptID 895 0) setCycle: BegLoop self)
			)
			(4
				(if register
					(boyClothes dispose:)
					((ScriptID 895 0) get: 7 normalize:)
				else
					(babyClothes dispose:)
					((ScriptID 895 0) get: 8 normalize:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance boyClothes of Actor
	(properties
		x 196
		y 39
		noun 6
		view 250
		priority 4
		signal $4810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (< y 100)
					(curRoom setScript: jumpForClothes 0 3)
				else
					(curRoom setScript: pickupClothes 0 1)
				)
			)
			(25
				(if local1
					(messager say: 6 25 2)
				else
					(curRoom setScript: sGetBoyClothes)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance babyClothes of Actor
	(properties
		x 247
		y 35
		noun 2
		approachX 209
		approachY 126
		view 250
		cel 2
		priority 4
		signal $4810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if local0
					(curRoom setScript: pickupClothes 0 0)
				else
					(curRoom setScript: jumpForClothes 0 4)
				)
			)
			(25
				(if local0
					(messager say: 2 25 2)
				else
					(curRoom setScript: sGetBabyClothes)
				)
			)
			(6
				(if local0
					(messager say: 2 6 2)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bone of Actor
	(properties
		x 220
		y 143
		noun 5
		view 250
		cel 4
		signal $4810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 5 6 2))
			(7
				(curRoom setScript: sGetBone)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance Bone of TWInvItem
	(properties
		loop 1
		cel 7
		message 25
		signal $0002
		noun 5
		modNum 250
	)
)

(instance Boy_s_Clothes of TWInvItem
	(properties
		loop 1
		cel 8
		message 26
		signal $0002
		noun 28
		modNum 250
		name "Boy's Clothes"
	)
)

(instance Baby_s_Clothes of TWInvItem
	(properties
		loop 1
		cel 9
		message 27
		signal $0002
		noun 27
		modNum 250
		name "Baby's Clothes"
	)
)

(instance pepperActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(26
				(if local0
					(curRoom setScript: sChangeClothes)
				else
					(messager say: 20 120 15)
				)
			)
			(27 (messager say: 20 121))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance watchMan of Actor
	(properties
		x 161
		y 92
		noun 25
		view 815
		loop 3
		priority 2
		signal $4810
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(84
				(theMusic2 number: 927 setLoop: 1 play:)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lockjawActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(7
					(if (not (Btst 224))
						(curRoom setScript: sGetBone)
					else
						(messager say: 18 7)
					)
				)
				(6 (messager say: 18 6))
				(25
					(curRoom setScript: ljGetBone)
					(return 1)
				)
				(26 (messager say: 18 120))
				(27
					(if (Btst 10)
						(curRoom setScript: sWrapDog)
					else
						(messager say: 18 121 10)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance hole of View
	(properties
		x 234
		y 24
		z -100
		noun 24
		view 250
		cel 5
		priority 3
		signal $4810
	)
)

(instance barrel of Feature
	(properties
		y 170
		noun 3
	)
)

(instance blueApron of Feature
	(properties
		x 264
		y 149
		noun 4
		nsTop 35
		nsLeft 251
		nsBottom 64
		nsRight 277
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 10)
					(messager say: 29 7)
				else
					((ScriptID 895 0) setScript: jumpForClothes 0 1)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance clothesLine of Feature
	(properties
		y 47
		noun 7
	)
)

(instance crate1 of Feature
	(properties
		y 160
		noun 8
	)
)

(instance crate2 of Feature
	(properties
		y 100
		noun 9
	)
	
	(method (doVerb theVerb)
		(= global215 67)
		(super doVerb: theVerb)
	)
)

(instance crate3 of Feature
	(properties
		x 91
		y 89
		noun 10
		nsTop 73
		nsLeft 72
		nsBottom 105
		nsRight 110
		sightAngle 40
	)
)

(instance crate4 of Feature
	(properties
		y 120
		noun 11
	)
)

(instance crate5 of Feature
	(properties
		y 90
		noun 12
	)
	
	(method (doVerb theVerb)
		(= global215 93)
		(super doVerb: theVerb)
	)
)

(instance crate6 of Feature
	(properties
		x 183
		y 107
		noun 13
		nsTop 71
		nsLeft 175
		nsBottom 101
		nsRight 192
		sightAngle 40
	)
)

(instance crate7 of Feature
	(properties
		x 232
		y 107
		noun 14
		nsTop 85
		nsLeft 223
		nsBottom 103
		nsRight 242
		sightAngle 40
	)
)

(instance crate8 of Feature
	(properties
		x 227
		y 107
		noun 15
		nsTop 85
		nsLeft 210
		nsBottom 118
		nsRight 245
		sightAngle 40
	)
)

(instance girlSuit of Feature
	(properties
		x 219
		y 110
		noun 16
		nsTop 43
		nsLeft 209
		nsBottom 75
		nsRight 230
		sightAngle 40
	)
)

(instance leaves of Feature
	(properties
		y 10
		noun 17
	)
)

(instance pebbles1 of Feature
	(properties
		y 134
		noun 19
		approachX 208
		approachY 134
		approachDist 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (not (ego has: 4))
					(curRoom setScript: pickUpPebbles)
				else
					(messager say: 19 7 13)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pinkCoat of Feature
	(properties
		x 137
		y 114
		noun 21
		nsTop 19
		nsLeft 122
		nsBottom 70
		nsRight 153
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 10)
					(messager say: 29 7)
				else
					(curRoom setScript: jumpForClothes 0 2)
				)
			)
			(25
				((ScriptID 895 0) setHeading: 180)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance boyClothesFeature of Feature
	(properties
		x 191
		y 103
		noun 6
		nsTop 32
		nsLeft 170
		nsBottom 75
		nsRight 213
	)
	
	(method (doVerb theVerb)
		(boyClothes doVerb: theVerb)
	)
)

(instance babyClothesFeature of Feature
	(properties
		x 242
		y 152
		noun 2
		nsTop 34
		nsLeft 226
		nsBottom 70
		nsRight 259
	)
	
	(method (doVerb theVerb)
		(babyClothes doVerb: theVerb)
	)
)

(instance northEFeature of ExitFeature
	(properties
		nsTop 55
		nsLeft 189
		nsBottom 106
		nsRight 237
		cursor 912
		exitDir 1
		modNum 250
		noun 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(= global215 16)
				(super doVerb: theVerb)
			)
			(7
				(if (not (ego has: 4))
					(curRoom setScript: pickUpPebbles)
				else
					(messager say: 19 7 13)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance watchManTalker of BalloonTalker
	(properties
		talkWidth 140
		tailPosn 4
	)
	
	(method (init)
		(= x (- (watchMan x?) 160))
		(= y (- (watchMan y?) 25))
		(super init: &rest)
	)
)

(instance lockjawPoly of Polygon
	(properties)
)

(instance roomPoly of Polygon
	(properties)
)

(instance roomTimer of Timer
	(properties)
)

(instance sFx of Sound
	(properties)
)
