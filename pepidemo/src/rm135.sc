;;; Sierra Script 1.0 - (do not remove this comment)
(script# 135)
(include sci.sh)
(use Main)
(use BalloonTalker)
(use TWRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm135 0
	fredTalker 19
)

(local
	[newReaderLetters 200]
	local200
	local201
	[theCel 200]
	[theTheCel 73] = [1 4 13 9 0 12 8 13 36 5 17 0 13 10 11 8 13 38 36 18 4 11 5 36 3 4 19 4 17 12 8 13 0 19 8 14 13 37 4 16 20 0 11 8 19 24 37 3 4 12 14 2 17 0 2 24 36 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1]
	[theTheCel_2 36] = [19 0 17 6 4 19 38 36 15 7 8 11 8 3 4 11 15 7 8 0 38 36 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1]
)
(procedure (localproc_0f53 param1 &tmp temp0 temp1)
	(= temp1 param1)
	(= temp0 0)
	(while (<= temp0 75)
		(switch temp1
			(1
				(= [theCel temp0] [theTheCel temp0])
			)
			(2
				(= [theCel temp0] [theTheCel_2 temp0])
			)
		)
		(++ temp0)
	)
)

(instance rm135 of ADRoom
	(properties
		noun 1
		picture 135
		style $000e
		north 140
		vanishingY -60
	)
	
	(method (init)
		(super init: &rest)
		(theMusic number: 135 setLoop: -1 flags: 1 play:)
		(theGame setEgo: (ScriptID 895 0) handsOn:)
		(ego
			init:
			view: 135
			setLoop: 1
			cel: 0
			posn: 91 164
			setCycle: Walk
		)
		((ScriptID 895 1)
			init:
			view: 132
			setLoop: 6
			cel: 6
			posn: 83 240
			approachX: 93
			approachY: 158
			setCycle: 0
		)
		((ScriptID 2004 0) winX: 105 winY: 175 tailPosn: 0)
		(windowFeature init: setOnMeCheck: 1 16)
		(sky init: setOnMeCheck: 1 4)
		(trees init: setOnMeCheck: 1 256)
		(appleTree init: setOnMeCheck: 1 16384)
		(peppersRoof init: setOnMeCheck: 1 2)
		(walkHandler addToFront: peppersRoof)
		(walkHandler add: curRoom)
		(roofTops init: setOnMeCheck: 1 32)
		(upperPeppersRoof init: setOnMeCheck: 1 2048)
		(obrienWindow init: setOnMeCheck: 1 128)
		(obrienHouse init: setOnMeCheck: 1 64)
		(theGame handsOn:)
		(self setScript: onPipeScr)
	)
	
	(method (dispose)
		(walkHandler delete: peppersRoof delete: curRoom)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 3)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (newRoom)
		(theMusic fade:)
		(super newRoom: &rest)
	)
)

(instance onPipeScr of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(User canInput: 1 canControl: 0)
	)
	
	(method (dispose)
		(Bset 150)
		(peppersRoof init:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: 1 canControl: 0)
				(messager say: 9 7 2 1)
			)
			(1
				(theGame handsOff:)
				(ego setLoop: 1 setSpeed: 10 setCycle: End self)
				(theMusic2 number: 1352 flags: 1 setLoop: 1 play:)
			)
			(2
				((ScriptID 2004 0) winX: 105 winY: 175 tailPosn: 0)
				(messager say: 9 7 2 2 self)
			)
			(3 (= ticks 80))
			(4
				((ScriptID 2004 0)
					winX: 86
					winY: 165
					tailPosn: 0
					talkWidth: 100
				)
				(messager say: 9 7 2 3 4 self)
			)
			(5 (= ticks 80))
			(6
				((ScriptID 895 0)
					setLoop: 2
					cel: 0
					setCycle: CT 5 1 self
				)
			)
			(7
				((ScriptID 895 0) setCycle: End self)
				(theMusic2 number: 1354 setLoop: 1 flags: 1 play:)
			)
			(8
				((ScriptID 895 1) view: 807 setLoop: 8 posn: 76 160)
				(= cycles 15)
			)
			(9
				((ScriptID 2004 0)
					winX: 90
					winY: 145
					tailPosn: 0
					talkWidth: 100
				)
				(messager say: 9 7 2 5 6 self)
			)
			(10 (= ticks 10))
			(11
				(ego
					normalize:
					setSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 107 163
				)
				(= seconds 1)
			)
			(12
				(ego normalize: 800)
				(= cycles 1)
			)
			(13
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								103
								162
								104
								166
								184
								166
								229
								134
								255
								125
								286
								121
								319
								121
								319
								118
								208
								118
								186
								150
								168
								152
								167
								158
								140
								158
								111
								158
								105
								149
								103
								156
							yourself:
						)
				)
				(= cycles 2)
			)
			(14
				(ego
					ignoreActors: 1
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 139 165 self
				)
			)
			(15
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sky of Feature
	(properties
		noun 2
	)
)

(instance trees of Feature
	(properties
		noun 8
	)
)

(instance roofTops of Feature
	(properties
		noun 5
	)
)

(instance appleTree of Feature
	(properties
		noun 11
	)
)

(instance windowFeature of Feature
	(properties
		noun 3
		approachX 137
		approachY 159
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 6 7)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(theGame points: 206 2)
				(if (Btst 150)
					(if (not (Btst 68))
						(Bset 68)
						(theGame points: 207 1)
						(curRoom setScript: cleanWindowScr)
					else
						(messager say: 3 7 5)
					)
				else
					(messager say: 3 7 8)
				)
			)
			(6
				(cond 
					((not (Btst 150)) (messager say: 3 6 8))
					((not (Btst 68)) (messager say: (self noun?) 6 1))
					(else (curRoom setScript: cartoonAndFallInScr))
				)
				(Bset 143)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance obrienWindow of Feature
	(properties
		noun 7
	)
)

(instance obrienHouse of Feature
	(properties
		noun 6
	)
)

(instance cleanWindowScr of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (Btst 143) (= next cartoonAndFallInScr))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					setSpeed: 5
					setMotion:
						MoveTo
						(windowFeature approachX?)
						(windowFeature approachY?)
						self
				)
			)
			(2
				(if (Btst 143)
					(messager say: 3 7 6 1 self)
				else
					(messager say: 3 7 7 1 self)
				)
			)
			(3
				(theMusic2 number: 1355 loop: 1 play:)
				(= ticks 130)
				(ego view: 135 setLoop: 3 cel: 0 setCycle: Fwd)
			)
			(4
				(ego normalize: 800)
				(= cycles 2)
			)
			(5
				(if (Btst 143)
					(messager say: 3 7 6 2 self)
				else
					(messager say: 3 7 7 2 self)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cartoonAndFallInScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic fade:)
				(= cycles 1)
			)
			(1
				(curRoom drawPic: 141 10)
				(theMusic number: 136 flags: 1 setLoop: -1 play:)
				(uncleFred init: view: 140 loop: 1 cel: 0 posn: 262 153)
				((ScriptID 895 0) dispose:)
				((ScriptID 895 1) dispose:)
				(book init: stopUpd:)
				(= cycles 20)
			)
			(2
				(= global215 72)
				(fredTalker x: 37 y: 85 tailPosn: 1)
				(messager say: 12 0 3 1 4 self 140)
			)
			(3 (= ticks 50))
			(4
				(uncleFred setCycle: CT 4 1 self)
			)
			(5
				(book dispose:)
				(= cycles 1)
			)
			(6
				(uncleFred cel: 5 setCycle: End self)
			)
			(7
				(uncleFred setLoop: 2 cel: 0 setCycle: End self)
			)
			(8
				(fredTalker x: 32 y: 86 tailPosn: 1)
				(messager say: 12 0 3 5 6 self 140)
			)
			(9 (= ticks 60))
			(10
				(uncleFred setCycle: Beg self)
			)
			(11
				(uncleFred setLoop: 1 cel: 8 setCycle: CT 5 -1 self)
			)
			(12
				(book init: stopUpd:)
				(= cycles 1)
			)
			(13
				(uncleFred cel: 4 setCycle: CT 0 -1 self)
			)
			(14 (= ticks 40))
			(15
				(uncleFred
					view: 153
					setLoop: 0
					cel: 0
					posn: 265 155
					setCycle: End self
				)
				(theMusic2 number: 1410 setLoop: 1 flags: 1 play:)
			)
			(16
				(microBen
					init:
					view: 139
					setLoop: 0
					cel: 0
					posn: 27 115
					setPri: 15
				)
				(= ticks 20)
			)
			(17
				(localproc_0f53 1)
				(= ticks 180)
			)
			(18
				(self setScript: readerBoardScr)
				(= cycles 5)
			)
			(19
				(fredTalker x: 55 y: 100 tailPosn: 4)
				(messager say: 12 0 3 7 8 self 140)
			)
			(20
				(uncleFred view: 141 setLoop: 4 cel: 0 setCycle: End self)
			)
			(21
				(fredTalker x: 48 y: 110 tailPosn: 4)
				(messager say: 12 0 3 9 self 140)
			)
			(22
				(uncleFred setLoop: 5 setCycle: End self)
			)
			(23
				(messager say: 12 0 3 10 self 140)
			)
			(24
				(uncleFred
					view: 153
					setLoop: 0
					cel: 0
					posn: 265 155
					setCycle: End self
				)
				(theMusic2 number: 1410 setLoop: 1 flags: 1 play:)
			)
			(25
				(fredTalker x: 55 y: 100 tailPosn: 4)
				(messager say: 12 0 3 11 self 140)
			)
			(26
				(microBen
					init:
					view: 139
					setLoop: 0
					cel: 0
					posn: 27 115
					setPri: 15
					setCycle: End self
				)
			)
			(27 (= ticks 40))
			(28
				(microBen setLoop: 1 cel: 0 setCycle: Fwd)
				(= ticks 120)
			)
			(29 (= ticks 30))
			(30
				(messager say: 12 0 3 12 self 140)
			)
			(31
				(book dispose:)
				(readerBoardScr register: 1)
				(= ticks 220)
			)
			(32
				(uncleFred dispose:)
				(microBen dispose:)
				(theMusic fade:)
				((ScriptID 895 0)
					init:
					view: 135
					setLoop: 5
					cel: 0
					posn: 136 157
					setCycle: 0
				)
				(curRoom drawPic: 135 10)
				(= cycles 3)
			)
			(33
				(alternateDoggie
					view: 135
					loop: 4
					cel: 0
					posn: 144 157
					setSpeed: 7
					setCycle: Fwd
					init:
				)
				((ScriptID 2004 0)
					winX: 151
					winY: 131
					tailPosn: 0
					talkWidth: 100
				)
				(theMusic number: 135 setLoop: -1 play:)
				(messager say: 10 0 4 0 self)
				(alternateWindow
					view: 136
					loop: 0
					cel: 0
					posn: 138 100
					init:
					stopUpd:
				)
			)
			(34
				(alternateWindow setCycle: End self)
			)
			(35
				(alternateDoggie dispose:)
				(theMusic2 number: 1357 flags: 1 setLoop: 1 play:)
				((ScriptID 895 0)
					view: 135
					setSpeed: 5
					setLoop: 5
					cel: 0
					setCycle: End self
				)
			)
			(36
				(theGame handsOn:)
				(self dispose:)
				(curRoom newRoom: 140)
				(= cycles 1)
			)
		)
	)
)

(instance uncleFred of Actor
	(properties
		x 248
		y 154
		view 143
		loop 1
		cel 7
	)
)

(instance alternateDoggie of Actor
	(properties
		x 143
		y 159
		view 135
		loop 5
		cel 4
		priority 15
		signal $4010
	)
)

(instance alternateWindow of Prop
	(properties
		x 138
		y 100
		view 136
	)
)

(instance peppersRoof of Feature
	(properties
		noun 9
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 3 7)
			(theGame points: 205 2)
			(walkHandler delete: peppersRoof delete: curRoom)
			(if (< (onPipeScr state?) 1) (onPipeScr cue:))
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance upperPeppersRoof of Feature
	(properties
		noun 4
	)
)

(instance readerBoardScr of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 21])
		(switch (= state newState)
			(0
				(if (not register)
					(= [newReaderLetters local200] (readerLetters new:))
					(if
					([newReaderLetters local200] respondsTo: #setMotion)
						([newReaderLetters local200]
							init:
							moveSpeed: 3
							setMotion: MoveTo 9 19 [newReaderLetters local200]
						)
						(thadList add: [newReaderLetters local200])
						(= cycles 3)
					else
						(-- state)
						(self cue:)
					)
				else
					(++ state)
					(= cycles 2)
				)
			)
			(1
				(++ local200)
				(cond 
					(register (self cue:))
					((!= [theCel local200] -1) (= state -1) (= cycles 2))
					(else (self cue:))
				)
			)
			(2
				(thadList
					eachElementDo: #dispose
					eachElementDo: #delete
					release:
					dispose:
				)
				(= ticks 320)
			)
			(3 (self dispose:))
		)
	)
)

(instance thadList of Set
	(properties)
)

(instance readerLetters of Actor
	(properties
		x 118
		y 19
		view 138
		loop 2
		cel 5
		priority 8
		signal $6810
	)
	
	(method (init param1 &tmp temp0)
		(cond 
			((== [theCel local200] -1) 0)
			((< [theCel local200] 16) (self setLoop: 1) (= cel [theCel local200]))
			((< [theCel local200] 26) (self setLoop: 2) (= cel (- [theCel local200] 16)))
			((< [theCel local200] 36) (self setLoop: 0) (= cel (- [theCel local200] 26)))
			((OneOf [theCel local200] 36 37 38)
				(self setLoop: 0)
				(switch [theCel local200]
					(36 (= cel 10))
					(37 (= cel 11))
					(38 (= cel 12))
				)
			)
		)
		(if (> [theCel local200] -1)
			(theMusic2 number: 1401 setLoop: 1 flags: 1 play:)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(return 0)
	)
	
	(method (cue)
		(super cue:)
		(++ local201)
		(self dispose:)
	)
)

(instance microBen of Actor
	(properties
		view 139
	)
)

(instance target of Prop
	(properties
		x 257
		y 37
		view 146
		loop 1
		priority 3
	)
)

(instance book of View
	(properties
		x 284
		y 139
		view 137
		cel 3
	)
)

(instance fredTalker of BalloonTalker
	(properties
		x 88
		y 80
		talkWidth 180
		tailPosn 1
	)
)
