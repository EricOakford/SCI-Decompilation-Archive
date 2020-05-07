;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm460 0
)

(local
	[newABee 9]
	[local9 7] = [300 120 207 260 70 5 140]
	[local16 7] = [11 11 41 23 23 23 23]
	[local23 7] = [0 4 2 4 2 2]
)
(procedure (localproc_1eb7 &tmp temp0)
	(= temp0 0)
	(while (< temp0 7)
		((= [newABee temp0] (aBee new:))
			x: [local9 temp0]
			y: [local16 temp0]
			z: [local23 temp0]
			init:
		)
		(++ temp0)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance wormOut of Sound
	(properties
		number 462
	)
)

(instance wormIn of Sound
	(properties
		number 463
	)
)

(instance rm460 of KQ6Room
	(properties
		noun 3
		picture 460
	)
	
	(method (init)
		(theGame handsOn:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						189
						0
						189
						0
						141
						42
						187
						92
						187
						130
						176
						164
						183
						189
						177
						228
						185
						317
						150
						317
						123
						243
						123
						229
						129
						199
						113
						193
						119
						170
						117
						172
						108
						255
						111
						305
						109
						298
						104
						269
						103
						258
						106
						242
						106
						232
						100
						194
						98
						178
						94
						162
						95
						155
						106
						125
						106
						104
						113
						69
						112
						44
						114
						15
						106
						19
						103
						89
						103
						90
						100
						0
						97
						0
						0
						319
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 181 132 181 136 175 142 144 143 115 140 108 134 117 129 174 129
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						308
						138
						302
						142
						291
						139
						286
						139
						282
						152
						267
						152
						265
						144
						259
						139
						243
						138
						240
						135
						247
						126
						304
						126
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 256 149 246 157 204 157 197 151 201 143 249 143
					yourself:
				)
		)
		(super init: &rest)
		(localproc_1eb7)
		(widow init:)
		(Lock 143 modNum 0)
		(if (== prevRoomNum 461)
			(ego
				posn: 74 182
				loop: 2
				setScale: Scaler 100 40 135 0
				init:
			)
			(cond 
				(((ScriptID 40 0) spiderBit?)
					(ego posn: 65 187)
					(curRoom setScript: widowKillsAlex 0 801)
				)
				(((ScriptID 40 0) parchmentBit?)
					(ego posn: 65 187)
					(curRoom setScript: widowKillsAlex 0 802)
				)
				(((ScriptID 40 0) gotParchment?)
					(ego posn: 59 172)
					((ScriptID 40 0) gotParchment: 0)
					(curRoom setScript: lookAtParchment)
				)
				(else
					(ego posn: 68 176)
					(theGlobalSound fade: 0 10 10)
					(theMusic
						number: 460
						setLoop: -1
						setVol: 0
						play:
						fade: 127 10 10
					)
				)
			)
		else
			(curRoom setScript: egoEnters)
			(theMusic number: 460 setLoop: -1 play:)
		)
		(if (not (Btst 136)) (scrapOfPaper init: stopUpd:))
		(features
			add:
				bookWormBookPile
				otherBookPiles
				oxymoronBookPile
				dipthongBookPile
				cBrownBookPile
				cLGreyBookPile
				cLBlueBookPile
				web
			eachElementDo: #init
		)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((ego inRect: 29 177 112 189) (curRoom setScript: widowKillsAlex 0 3))
			((ego edgeHit?) (theMusic fade: 0 10 10) (curRoom setScript: egoExits))
		)
		(super doit:)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 1 135
					setScale: Scaler 100 40 135 0
					init:
					setMotion: PolyPath 16 135 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (- (ego x?) 25) (ego y?) self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance bookworm of Prop
	(properties
		x 140
		y 139
		z 40
		noun 4
		view 463
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(94
				(self setScript: 0)
				(theGame handsOff:)
				(participle4U start: 5)
				(curRoom setScript: participle4U)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(5
				(super doVerb: theVerb &rest)
			)
			(else 
				(theGame handsOff:)
				(self setScript: inventOnWorm 0 theVerb)
			)
		)
	)
)

(class ScriptFeature of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		script 0
	)
	
	(method (init)
		(self approachVerbs: 1)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(cond 
					((bookworm script?) (messager say: 5 5 19 1))
					((== ((inventory at: 29) owner?) curRoomNum) (messager say: 5 5 6 1))
					(else (curRoom setScript: rummage 0 self))
				)
			)
			(2
				(cond 
					((bookworm script?) (messager say: 5 5 19 1))
					((== ((inventory at: 29) owner?) curRoomNum) (messager say: 5 5 6 1))
					((not (Btst 40)) (messager say: 5 2 7 1))
					((Btst 7) (curRoom setScript: callBooks 0 0))
					(else (Bset 60) (curRoom setScript: callBooks 0 1))
				)
			)
			(94
				(theGame handsOff:)
				(curRoom setScript: participle4U)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(else 
				(self setScript: inventOnBooks 0 theVerb)
			)
		)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(if newScript (newScript init: self &rest))
	)
)

(instance bookWormBookPile of ScriptFeature
	(properties
		x 138
		y 135
		noun 5
		onMeCheck $0008
		approachX 115
		approachY 146
	)
)

(instance otherBookPiles of ScriptFeature
	(properties
		x 298
		y 115
		noun 5
		onMeCheck $0004
		approachX 268
		approachY 131
	)
)

(instance cBrownBookPile of ScriptFeature
	(properties
		x 188
		y 116
		noun 5
		onMeCheck $0040
		approachX 186
		approachY 123
	)
)

(instance cLGreyBookPile of ScriptFeature
	(properties
		x 230
		y 153
		noun 5
		onMeCheck $0080
		approachX 233
		approachY 163
	)
)

(instance cLBlueBookPile of ScriptFeature
	(properties
		x 275
		y 147
		noun 5
		onMeCheck $0200
		approachX 249
		approachY 141
	)
)

(instance oxymoronBookPile of ScriptFeature
	(properties
		x 84
		y 107
		noun 5
		onMeCheck $0002
		approachX 66
		approachY 120
	)
)

(instance dipthongBookPile of ScriptFeature
	(properties
		x 223
		y 114
		noun 5
		onMeCheck $0020
		approachX 208
		approachY 127
	)
)

(instance rummage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (register approachX?) (register approachY?) self
				)
			)
			(1
				(if (== register bookWormBookPile)
					(ego
						view: 461
						normal: 0
						posn: (+ (register approachX?) 2) (register approachY?)
						setLoop: 4
						cel: 0
						cycleSpeed: 6
						setCycle: End self
					)
				else
					(ego
						view: 467
						setLoop: (if (== register cLBlueBookPile) 1 else 0)
						cel: 0
						normal: 0
						posn: (- (register approachX?) 2) (+ (register approachY?) 1)
						cycleSpeed: 6
						setCycle: End self
					)
				)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego
					posn: (- (ego x?) 2) (ego y?)
					reset: (if (== register cLBlueBookPile) 0 else 6)
				)
				(= cycles 8)
			)
			(4
				(cond 
					((not (Btst 7)) (curRoom setScript: handsOffTheGoods 0 register))
					((not (Btst 61)) (curRoom setScript: askForParti 0 register))
					((== ((inventory at: 36) owner?) curRoomNum) (curRoom setScript: anythingYet 0 register))
					(else (theGame handsOn:) (self dispose:))
				)
			)
		)
	)
)

(instance handsOffTheGoods of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 40)
				(if (== register bookWormBookPile)
					(self cue:)
				else
					(ego setMotion: PolyPath 107 142 self)
				)
				(wormOut play:)
				(bookworm init: setCycle: End)
			)
			(1
				(if (!= register bookWormBookPile) (ego setHeading: 45))
				(= cycles 10)
			)
			(2
				(bookworm setLoop: 4 cel: 0 setCycle: End self)
			)
			(3
				(bookworm setLoop: 5 cel: 14)
				(= cycles 8)
			)
			(4
				(myConv add: -1 5 5 3 1 add: -1 5 5 3 2 init: self)
			)
			(5
				(wormIn play:)
				(bookworm setLoop: 5 cel: 14 setCycle: Beg self)
			)
			(6
				(theGame handsOn:)
				(bookworm dispose:)
				(LoadMany 0 1044 1046 1045 1007)
				(self dispose:)
			)
		)
	)
)

(instance askForParti of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 40)
				(Bset 61)
				(cond 
					((== register callBooks)
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(self cue:)
					)
					((== register bookWormBookPile)
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(self cue:)
					)
					(else
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(ego setMotion: PolyPath 107 142 self)
					)
				)
			)
			(1)
			(2
				(if (!= register bookWormBookPile) (ego setHeading: 45))
				(= cycles 10)
			)
			(3
				(bookworm setLoop: 4 cel: 0 setCycle: End self)
			)
			(4
				(myConv
					add: -1 5 5 4 1
					add: -1 5 5 4 2
					add: -1 5 5 4 3
					add: -1 5 5 4 4
					add: -1 5 5 4 5
					init: self
				)
			)
			(5
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(6
				(bookworm cel: 0)
				(= cycles 4)
			)
			(7 (messager say: 5 5 4 6 self))
			(8
				(oxymoron init:)
				(self setScript: oxyOut self)
			)
			(9 (messager say: 5 5 4 7 self))
			(10
				(self setScript: oxyIn self)
			)
			(11
				(dipthong init:)
				(self setScript: dipthOut self)
			)
			(12
				(messager say: 5 5 4 8 self)
			)
			(13
				(self setScript: dipthIn self)
			)
			(14
				(myConv add: -1 5 5 4 9 add: -1 5 5 4 10 init: self)
			)
			(15
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(16
				(bookworm cel: 0)
				(= seconds 3)
			)
			(17
				(myConv add: -1 5 5 4 11 add: -1 5 5 4 12 init: self)
			)
			(18
				(if (ego has: 29)
					(theGame handsOff:)
					(participle4U start: 5)
					(curRoom setScript: participle4U)
				else
					(bookworm setLoop: 2 cel: 0 setCycle: End self)
				)
			)
			(19
				(bookworm cel: 0)
				(= cycles 4)
			)
			(20
				(myConv add: -1 5 5 4 13 add: -1 5 5 4 14 init: self)
			)
			(21
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(22
				(bookworm cel: 0)
				(= cycles 4)
			)
			(23
				(myConv add: -1 5 5 4 15 add: -1 5 5 4 16 init: self)
			)
			(24
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(25
				(bookworm cel: 0)
				(= cycles 4)
			)
			(26
				(myConv add: -1 5 5 4 17 add: -1 5 5 4 18 init: self)
			)
			(27
				(wormIn play:)
				(bookworm setLoop: 5 cel: 14 setCycle: Beg self)
			)
			(28
				(theGame handsOn:)
				(bookworm dispose:)
				(LoadMany 0 1044 1046 1045 1007)
				(self dispose:)
			)
		)
	)
)

(instance anythingYet of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((== register callBooks)
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(self cue:)
					)
					((== register bookWormBookPile)
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(self cue:)
					)
					(else
						(wormOut play:)
						(bookworm init: setCycle: End self)
						(ego setMotion: PolyPath 107 142 self)
					)
				)
			)
			(1)
			(2
				(if (!= register bookWormBookPile) (ego setHeading: 45))
				(= cycles 10)
			)
			(3 (messager say: 5 5 5 1 self))
			(4
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(5
				(bookworm cel: 0)
				(= cycles 4)
			)
			(6
				(if (Btst 120)
					(= temp0 (Random 1 5))
					(messager say: 5 5 18 temp0 self)
				else
					(Bset 120)
					(self setScript: stupidOxyScript self)
				)
			)
			(7
				(bookworm setLoop: 2 cel: 0 setCycle: End self)
			)
			(8
				(bookworm cel: 0)
				(= cycles 3)
			)
			(9 (messager say: 5 5 5 4 self))
			(10
				(theGame handsOn:)
				(bookworm setScript: waitForAnswer)
				(self dispose:)
			)
		)
	)
)

(instance stupidOxyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oxymoron init:)
				(self setScript: oxyOut self)
			)
			(1 (messager say: 5 5 5 2 self))
			(2 (self setScript: oxyIn self))
			(3 (self dispose:))
		)
	)
)

(instance waitForAnswer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bookworm stopUpd:)
				(= seconds 10)
			)
			(1
				(theGame handsOff:)
				(ego
					normal: 0
					view: 461
					setLoop: 3
					cel: 0
					normal: 0
					setCycle: End self
				)
			)
			(2 (ego reset: 6) (= cycles 8))
			(3 (messager say: 1 0 2 1 self))
			(4
				(wormIn play:)
				(bookworm setLoop: 5 cel: 14 setCycle: Beg self)
			)
			(5
				(bookworm dispose:)
				(LoadMany 0 1044 1046 1045 1007)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance callBooks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 107 142 self)
			)
			(1
				(ego setHeading: 90)
				(= cycles 10)
			)
			(2
				(if (Btst 61)
					(messager say: 5 2 5 1 self)
				else
					(messager say: 5 2 8 1 self)
				)
			)
			(3
				(cond 
					((== register 1) (curRoom setScript: talkGoAway))
					((Btst 61) (curRoom setScript: anythingYet 0 self))
					((Btst 7) (curRoom setScript: askForParti 0 self))
				)
			)
		)
	)
)

(instance talkGoAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(wormOut play:)
				(bookworm init: setCycle: End self)
			)
			(1 (messager say: 5 2 8 2 self))
			(2
				(wormIn play:)
				(bookworm setCycle: Beg self)
			)
			(3
				(theGame handsOn:)
				(bookworm dispose:)
				(self dispose:)
			)
		)
	)
)

(instance inventOnBooks of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (client approachX?) (client approachY?) self
				)
			)
			(1
				(= temp0
					(GetAngle (ego x?) (ego y?) (client x?) (client y?))
				)
				(ego setHeading: temp0 self)
			)
			(2 (messager say: 5 0 0 1 self))
			(3 (self dispose:))
		)
	)
)

(instance inventOnWorm of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (OneOf register 25 12 66 85 34)
					(messager say: 4 register 0 0 self)
				else
					(messager say: 4 0 0 0 self)
				)
			)
			(1
				(wormIn play:)
				(bookworm setLoop: 5 setCycle: Beg self)
			)
			(2
				(theGame handsOn:)
				(bookworm dispose:)
				(self dispose:)
			)
		)
	)
)

(instance parti of Actor
	(properties
		x 124
		y 136
		view 468
		priority 11
		signal $4010
		cycleSpeed 8
	)
)

(instance participle4U of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 98 134 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(messager say: 5 94 0 1 self)
			)
			(3
				(wormOut play:)
				(bookworm init: setCycle: End self)
			)
			(4
				(messager say: 5 94 0 2 self)
			)
			(5
				(theGame handsOff:)
				(messager say: 4 94 0 1 self)
			)
			(6
				(ego
					normal: 0
					view: 468
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					posn: 96 136
					setPri: 12
					setCycle: End self
				)
			)
			(7
				(parti init:)
				(bookworm setLoop: 3 cel: 0 setCycle: End self)
			)
			(8
				(ego posn: 98 134 reset: 0)
				(bookworm setLoop: 5 cel: 14)
				(= cycles 8)
			)
			(9
				(myConv
					add: -1 4 94 0 2
					add: -1 4 94 0 3
					add: -1 4 94 0 4
					init: self
				)
			)
			(10
				(oxymoron init:)
				(self setScript: oxyOut self)
			)
			(11
				(messager say: 4 94 0 5 self)
			)
			(12
				(self setScript: oxyIn self)
			)
			(13
				(dipthong init:)
				(self setScript: dipthOut self)
			)
			(14
				(messager say: 4 94 0 6 self)
			)
			(15
				(self setScript: dipthIn self)
			)
			(16 (parti setCycle: End self))
			(17
				(myConv
					add: -1 4 94 0 7
					add: -1 4 94 0 8
					add: -1 4 94 0 9
					init: self
				)
			)
			(18
				(parti dispose:)
				(wormIn play:)
				(bookworm setLoop: 5 cel: 14 setCycle: Beg self)
			)
			(19
				(wormOut play:)
				(bookworm init: setLoop: 1 cel: 0 setCycle: End self)
				(ego
					posn: 98 134
					reset: 6
					setMotion: PolyPath 109 154 self
				)
			)
			(20
				(ego setHeading: 45)
				(= cycles 6)
			)
			(21)
			(22
				(messager say: 4 94 0 10 self)
			)
			(23
				(ego
					normal: 0
					view: 461
					setLoop: 4
					cel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(24
				(theGame givePoints: 2)
				(wormIn play:)
				(bookworm setLoop: 5 setCycle: Beg self)
				(ego cycleSpeed: 1 setCycle: Beg)
			)
			(25
				(bookworm dispose:)
				(messager say: 4 94 0 11 self)
			)
			(26
				(theGame handsOn:)
				(ego posn: 109 154 get: 36 put: 29 curRoomNum reset: 6)
				(LoadMany 0 1044 1046 1045 1007)
				(self dispose:)
			)
		)
	)
)

(instance widow of Prop
	(properties
		x 70
		y 189
		z 35
		noun 11
		approachX 68
		approachY 176
		view 460
		loop 5
		priority 15
		signal $4010
		cycleSpeed 3
	)
	
	(method (init)
		(self approachVerbs: 1 2)
		(if (== prevRoomNum 461)
			(self cel: 12)
		else
			(self cel: 0)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: widowKillsAlex 0 803)
			)
			(1 (curRoom newRoom: 461))
			(2 (curRoom newRoom: 461))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance web of Feature
	(properties
		x 69
		y 189
		noun 8
		onMeCheck $0010
		approachX 68
		approachY 176
	)
	
	(method (init)
		(self approachVerbs: 1 2)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom newRoom: 461))
			(5
				(curRoom setScript: widowKillsAlex 0 804)
			)
			(2 (curRoom newRoom: 461))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance scrapOfPaper of View
	(properties
		x 92
		y 147
		noun 8
		approachX 68
		approachY 176
		view 460
		priority 15
		signal $4010
	)
	
	(method (init)
		(self approachVerbs: 1 0)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (curRoom newRoom: 461))
			(5
				(curRoom setScript: widowKillsAlex 0 804)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance loveInset of View
	(properties
		x 65
		y 189
		z 94
		view 462
		loop 4
		priority 15
		signal $4010
	)
)

(instance lookAtParchment of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 12 5 12 1 self)
			)
			(2
				(ego
					view: 462
					setLoop: 7
					cel: 0
					normal: 0
					cycleSpeed: 8
					posn: 61 174
					setCycle: CT 1 1 self
				)
			)
			(3
				(soundFx2 number: 468 setLoop: 1 play:)
				(scrapOfPaper dispose:)
				(ego setCycle: End self)
				(Bset 136)
			)
			(4
				(loveInset init:)
				(= seconds 6)
			)
			(5
				(loveInset dispose:)
				(theGame givePoints: 2)
				(theGlobalSound fade: 0 10 10 self)
				(theMusic
					number: 460
					setLoop: -1
					setVol: 0
					play:
					fade: 127 10 10
				)
				(ego
					setLoop: 3
					cel: 0
					cycleSpeed: 6
					posn: 58 173
					setCycle: End self
				)
			)
			(6 0)
			(7
				(messager say: 12 5 12 2 self)
			)
			(8
				(ego posn: 59 172 reset: 2)
				(theGame handsOn:)
				(Bset 57)
				(self dispose:)
			)
		)
	)
)

(instance widowKillsAlex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch register
					(801
						(= state (+ state 2))
						(self cue:)
					)
					(802
						(= state (+ state 2))
						(self cue:)
					)
					(803
						(messager say: 11 5 0 1 self)
					)
					(804
						(messager say: 8 5 0 1 self)
					)
					(3 (self cue:))
				)
			)
			(1
				(ego setMotion: PolyPath 68 176 self)
			)
			(2
				(ego setMotion: MoveTo 65 187 self)
			)
			(3
				(if (== register 3)
					(ego
						view: 462
						setLoop: 0
						cel: 0
						normal: 0
						cycleSpeed: 8
						posn: 70 189
						setCycle: CT 8 1 self
					)
				else
					(ego
						view: 462
						setLoop: 5
						cel: 0
						normal: 0
						cycleSpeed: 8
						posn: 65 189
						setCycle: End self
					)
				)
				(widow setCycle: End self)
			)
			(4)
			(5
				(widow setLoop: 6 cel: 0 posn: 68 162 z: 0)
				(= cycles 3)
			)
			(6
				(switch register
					(801
						(messager say: 9 5 0 2 self)
					)
					(802
						(messager say: 12 5 11 2 self)
					)
					(803
						(messager say: 11 5 0 2 self)
					)
					(804
						(messager say: 8 5 0 2 self)
					)
					(3 (messager say: 8 3 0 1 self))
				)
			)
			(7
				(switch register
					(801
						(messager say: 9 5 0 3 self)
					)
					(802
						(messager say: 12 5 11 3 self)
					)
					(803
						(messager say: 11 5 0 3 self)
					)
					(804
						(messager say: 8 5 0 3 self)
					)
					(3 (messager say: 8 5 0 3 self))
				)
			)
			(8
				(if (== register 3)
					(ego view: 462 setLoop: 1 cel: 0 setCycle: End self)
				else
					(self cue:)
				)
			)
			(9
				(theGlobalSound stop:)
				(theMusic number: 466 setLoop: 1 play: self)
				(if (== register 3)
					(ego view: 462 setLoop: 2 cel: 0 setCycle: End self)
				else
					(ego
						view: 462
						setLoop: 6
						cel: 0
						posn: 63 187
						setCycle: End self
					)
				)
				(widow setCycle: End self)
			)
			(10)
			(11)
			(12
				(switch register
					(801
						(messager say: 9 5 0 4 self)
					)
					(802
						(messager say: 12 5 11 4 self)
					)
					(803
						(messager say: 11 5 0 4 self)
					)
					(else  (self cue:))
				)
			)
			(13
				(theGame handsOn:)
				((ScriptID 40 0) spiderBit: 0)
				((ScriptID 40 0) parchmentBit: 0)
				(if (== register 3) (EgoDead 4) else (EgoDead 3))
			)
		)
	)
)

(instance aBee of Actor
	(properties
		noun 14
		view 460
		loop 11
		signal $6800
	)
	
	(method (init)
		(beeLine start: z)
		(self
			setStep: 12 10
			setPri: 14
			setScript: (beeLine new:)
		)
		(super init:)
	)
)

(instance beeLine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCycle: Fwd
					setMotion: MoveTo (Random 5 50) (Random 5 60) self
				)
			)
			(1
				(client
					setLoop: 13
					cel: 0
					posn: (+ (client x?) 6) (client y?)
					setCycle: End self
				)
			)
			(2
				(client
					setLoop: 12
					setCycle: Fwd
					posn: (- (client x?) 9) (+ (client y?) 14)
				)
				(= ticks 2)
			)
			(3
				(client
					setMotion: MoveTo (Random 260 310) (Random 5 60) self
				)
			)
			(4
				(client
					setLoop: 14
					cel: 0
					posn: (- (client x?) 6) (client y?)
					setCycle: End self
				)
			)
			(5
				(client
					setLoop: 11
					posn: (+ (client x?) 12) (+ (client y?) 14)
					setCycle: Fwd
				)
				(= ticks 2)
			)
			(6
				(client
					setMotion: MoveTo (Random 5 50) (Random 5 60) self
				)
			)
			(7
				(= state (- state 6))
				(self cue:)
			)
		)
	)
)

(instance dipthong of Prop
	(properties
		x 234
		y 45
		noun 6
		view 460
		loop 8
		priority 14
		signal $4010
	)
)

(instance oxymoron of Prop
	(properties
		x 279
		y 57
		noun 7
		view 460
		loop 7
		priority 14
		signal $4010
	)
)

(instance oxyOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oxymoron init: setCycle: CT 4 1 self)
			)
			(1
				(oxyBook init:)
				(oxymoron cel: 5 posn: 284 34)
				(= cycles 3)
			)
			(2
				(oxymoron cel: 6 posn: 281 23)
				(= cycles 3)
			)
			(3
				(oxymoron cel: 7 posn: 280 23)
				(soundFx2 number: 464 setLoop: 1 play:)
				(= cycles 3)
			)
			(4
				(oxymoron stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance dipthOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dipthong init: setCycle: CT 4 1 self)
			)
			(1
				(dipBook init:)
				(dipthong cel: 5 posn: 238 20)
				(= cycles 3)
			)
			(2
				(dipthong cel: 6 posn: 234 13)
				(= cycles 3)
			)
			(3
				(dipthong cel: 7 posn: 235 14)
				(= cycles 3)
				(soundFx2 number: 464 setLoop: 1 play: self)
			)
			(4 (dipthong stopUpd:))
			(5 (self dispose:))
		)
	)
)

(instance oxyIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oxymoron cel: 6 posn: 281 23)
				(= cycles 3)
			)
			(1
				(oxymoron cel: 5 posn: 284 34)
				(= cycles 3)
			)
			(2
				(oxyBook dispose:)
				(oxymoron posn: 279 57 setCycle: Beg self)
			)
			(3
				(oxymoron dispose:)
				(self dispose:)
			)
		)
	)
)

(instance dipthIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dipthong cel: 6 posn: 234 13)
				(= cycles 3)
			)
			(1
				(dipthong cel: 5 posn: 238 20)
				(= cycles 3)
			)
			(2
				(dipBook dispose:)
				(dipthong posn: 234 45 setCycle: Beg self)
			)
			(3
				(dipthong dispose:)
				(self dispose:)
			)
		)
	)
)

(instance oxyBook of View
	(properties
		x 285
		y 71
		noun 7
		view 460
		loop 9
		cel 2
		priority 13
		signal $4010
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)

(instance dipBook of Prop
	(properties
		x 240
		y 52
		noun 6
		view 460
		loop 10
		cel 2
		priority 13
		signal $4010
	)
	
	(method (init)
		(self stopUpd:)
		(super init:)
	)
)
