;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmHotTub) ;380
(include game.sh)
(use Main)
(use Intrface)
(use rmHoneymoonSuite)
(use LLRoom)
(use RandCyc)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	[local0 2]
	local2 =  1
	clothesOnFloor
	local4
	[undressCycles 41] = [0 0 159 136 0 1 159 136 0 2 159 136 0 3 154 114 0 4 150 105 0 5 144 94 0 6 138 92 0 6 129 111 0 7 141 133 0 8 133 134 -32768]
	[followEveCycles 29] = [4 1 117 153 4 2 121 150 4 3 125 147 4 4 129 144 4 5 133 141 4 6 137 138 4 7 144 134 -32768]
	[dressCycles 33] = [6 0 118 154 6 1 124 151 6 2 130 149 6 3 136 146 6 4 142 144 6 5 148 141 6 6 154 139 6 7 159 136 -32768]
)
(procedure (BubblesOn)
	(= local2 1)
	(= local4 1)
	(aEve setLoop: 0 x: 85 y: 125 setCycle: Forward)
	(if (== (ego view?) 381)
		(ego setLoop: 1 setCel: 0 setCycle: Forward)
	)
	(globalSound number: 380 loop: -1 vol: 127 flags: mNOPAUSE play:)
	(rimJet1 init: cycleSpeed: howFast setCycle: Forward)
	(rimJet2 init: cycleSpeed: howFast setCycle: Forward)
	(rimJet3 init: cycleSpeed: howFast setCycle: RandCycle)
	(aJet init: cycleSpeed: howFast setCycle: RandCycle)
	(bJet init: cycleSpeed: howFast setCycle: RandCycle)
	(cJet init: cycleSpeed: howFast setCycle: RandCycle)
	(dJet init: cycleSpeed: howFast setCycle: RandCycle)
	(eJet init: cycleSpeed: howFast setCycle: RandCycle)
)

(procedure (BubblesOff)
	(= local2 6)
	(= local4 6)
	(aEve setLoop: 2 x: 85 y: 125 stopUpd:)
	(globalSound stop:)
	(if (== (ego view?) 381)
		(ego setLoop: 2 setCel: 0 setCycle: 0)
	)
	(rimJet1 dispose:)
	(rimJet2 dispose:)
	(rimJet3 dispose:)
	(aJet dispose:)
	(bJet dispose:)
	(cJet dispose:)
	(dJet dispose:)
	(eJet dispose:)
)

(instance rm380 of LLRoom
	(properties
		picture rmHotTub
	)
	
	(method (init)
		(LoadMany VIEW 381 382 380)
		(LoadMany SOUND 381 380)
		(if (not (Btst fFollowingEve))
			(Load SOUND 384)
			(Load VIEW 803)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189 0 189 0 0 203 -3 203 81 68 81 48 95
						54 114 129 114 165 141 166 158 163 163 319 111
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 108 172 108 148 91 319 73
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 88 92 122 92 122 97 88 97
					yourself:
				)
		)
		(ego actions: egoActions)
		(theEgoHead actions: egoActions)
		(aEve
			cycleSpeed: howFast
			moveSpeed: howFast
			setCycle: Forward
			approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook
			init:
		)
		(if (== prevRoomNum 385)
			(larryClothes init: stopUpd:)
			(ego
				view: 381
				egoSpeed: (* 2 howFast)
				setLoop: 1
				z: 0
				posn: 120 139
				init:
			)
			(if (Btst fBubblesOff) (BubblesOff) else (BubblesOn))
			(if (Btst fFollowingEve)
				(aEve
					cycleSpeed: (* 2 howFast)
					moveSpeed: (* 2 howFast)
					setLoop: 1
					setCel: 0
					posn: 84 126
				)
				(theMusic send: 3 78 1 send: 7 78 0 send: 10 78 0)
				(HandsOff)
				(curRoom setScript: sEveLeaves)
			else
				(theMusic send: 4 78 1 send: 9 78 1 send: 5 78 0)
			)
		else
			(if (Btst fBubblesOff) (BubblesOff) else (BubblesOn))
			(if (Btst fChasingDoll)
				(aDoll cycleSpeed: howFast moveSpeed: howFast init:)
				(ego posn: 231 113)
				(curRoom setScript: sFlyingDoll)
			else
				(self setScript: sFromLivingroom)
			)
		)
		(towel init: stopUpd: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
		(fStatue init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(fSpaButton init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
		(fHotels init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(fHotTub init:)
		(fLivingRoom init:)
		(fWaterfall init:)
		(ego init:)
		(super init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== local4 local2)
			(= local4 0)
			(Palette PALCycle 64 71 -1)
		else
			(++ local4)
		)
		(cond 
			(script)
			((and (ego mover?) (== (ego view?) 381)) (HandsOff) (curRoom setScript: sGetDressed))
			((IsObjectOnControl ego cBLUE)
				(if (< (ego y?) 100)
					(HandsOff)
					(self setScript: sToLivingroom)
				else
					(curRoom newRoom: rmLivingRoom)
				)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 380 0)
				(Print 380 1)
				(Print 380 2 #at -1 140)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance egoActions of Code	;EO: this was a class, but it is not in the class table
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (or (== theVerb verbDo) (== theVerb verbZipper))
				(cond 
					((not (Btst fInvitedIntoTub)) (Print 380 3) (Print 380 4 #at -1 140))
					((not (Btst fMetEve)) (Print 380 5))
					((!= (ego view?) 381) (curRoom setScript: sUndress) (return TRUE))
					(else (curRoom setScript: sGetDressed) (return TRUE))
				)
			else
				FALSE
			)
		)
	)
)

(instance sUndress of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== clothesOnFloor TRUE) (== (ego cel?) 7))
			(= clothesOnFloor FALSE)
			(sfxSplash play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 159 136 self)
			)
			(1
				(Print 380 6)
				(ego
					egoSpeed: (+ 1 howFast)
					view: 803
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(larryClothes init:)
				(= clothesOnFloor TRUE)
				(ego
					view: 381
					setLoop: 0
					setPri: 10
					setCel: 0
					setCycle: MoveCycle @undressCycles self
				)
			)
			(4
				(ego view: 381 setPri: -1 x: 120 y: 139)
				(if (Btst 17)
					(ego setLoop: 2 setCel: 0 setCycle: 0)
				else
					(ego setLoop: 1 setCycle: Forward)
				)
				(= seconds 3)
			)
			(5
				(Print 380 7)
				(fHotels approachVerbs: 0)
				(fStatue approachVerbs: 0)
				(fSpaButton approachVerbs: 0)
				(aEve approachVerbs: 0)
				(towel approachVerbs: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetDressed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 381 setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(ego
					egoSpeed: (+ 1 howFast)
					view: 381
					setCycle: MoveCycle @dressCycles self
				)
			)
			(2
				(larryClothes dispose:)
				(ego
					view: 803
					setLoop: 1
					setCel: 255
					posn: 159 136
					setCycle: BegLoop self
				)
			)
			(3
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(4
				(HandsOn)
				(NormalEgo 3)
				(fSpaButton approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
				(fStatue approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
				(aEve approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
				(fHotels approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
				(towel approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk)
				(self dispose:)
			)
		)
	)
)

(instance sEveLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aEve
					setLoop: 1
					setCel: 0
					posn: 84 126
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(towel dispose:)
				(aEve setCycle: EndLoop self)
			)
			(2
				(aEve
					setLoop: 3
					setCel: 0
					cycleSpeed: howFast
					moveSpeed: howFast
					posn: 96 121
					setCycle: Walk
					setMotion: PolyPath 166 118 self
				)
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(ego
					egoSpeed:
					cycleSpeed: howFast
					moveSpeed: howFast
					setLoop: 4
					setCel: 0
					posn: 117 153
					setCycle: MoveCycle @followEveCycles self
				)
			)
			(5 0)
			(6
				(Print 380 8 #at -1 20)
				(aEve setMotion: PolyPath 241 105)
				(= cycles 8)
			)
			(7
				(ego
					egoSpeed:
					setLoop: 5
					setCycle: Walk
					setMotion: PolyPath 230 113 self
				)
			)
			(8 (curRoom newRoom: 360))
		)
	)
)

(instance sToLivingroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego y?) 106)
					(ego setMotion: PolyPath 186 86 self)
				else
					(ego setMotion: PolyPath 240 110 self)
				)
			)
			(1 (curRoom newRoom: 360))
		)
	)
)

(instance sFromLivingroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 116)
					(ego posn: 186 84 setMotion: PolyPath 140 95 self)
				else
					(ego posn: 231 113 setMotion: PolyPath 205 120 self)
				)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sFlyingDoll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				;EO: Code modification taken from speed patch
				;(HandsOff)
				(HandsOn)
				(aDoll setCycle: Forward setMotion: MoveTo -20 90)
				(ego egoSpeed: howFast setMotion: PolyPath 170 131 self)
			)
			(1
				(aDoll setMotion: MoveTo -35 90)
				(soundFx fade: self)
			)
			(2
				(NormalEgo loopW)
				(HandsOn)
				(UnLoad VIEW 373)
				(UnLoad SOUND 372)
				(Bclr fChasingDoll)
				(aDoll dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sfxSplash of Sound
	(properties
		flags mNOPAUSE
		number 384
	)
)

(instance aEve of Actor
	(properties
		x 85
		y 125
		description {Eve}
		sightAngle 40
		approachX 148
		approachY 128
		view 382
	)
	
	(method (doVerb theVerb)
		(if (== (ego view?) 381)
			(HandsOn)
			(curRoom newRoom: 385)
			(return)
		)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fInvitedIntoTub) (Print 380 9))
					((Btst fMetEve) (Print 380 10) (Print 380 11 #at -1 140) (Bset 2))
					(else (Print 380 12))
				)
			)
			(verbTalk
				(cond 
					((Btst fInvitedIntoTub) (Print 380 13) (Print 380 14 #at -1 140))
					((Btst 7)
						(Print 380 15)
						(Print 380 16)
						(Print 380 17)
						(Print 380 18)
						(Print 380 19)
						(Bset fInvitedIntoTub)
					)
					(else
						(Print 380 20)
						(Print 380 21)
						(Print 380 11 #at -1 140)
						(Bset fMetEve)
					)
				)
			)
			(verbDo (Print 380 22))
			(else  (Print 380 23))
		)
	)
)

(instance aDoll of Actor
	(properties
		x 143
		y 95
		yStep 5
		view 373
		priority 8
		signal fixPriOn
		xStep 7
	)
)

(instance aJet of Prop
	(properties
		x 59
		y 147
		description {the jet}
		sightAngle 40
		view 380
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (Print 380 24))
			(else  (Print 380 25))
		)
	)
)

(instance bJet of Prop
	(properties
		x 61
		y 166
		description {the bubble}
		sightAngle 40
		view 380
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance cJet of Prop
	(properties
		x 79
		y 156
		description {the bubbles}
		sightAngle 40
		view 380
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance dJet of Prop
	(properties
		x 101
		y 164
		description {the bubbles}
		sightAngle 40
		view 380
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance eJet of Prop
	(properties
		x 99
		y 148
		description {the bubbles}
		sightAngle 40
		view 380
		signal ignrAct
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance rimJet1 of Prop
	(properties
		x 50
		y 127
		description {the bubbles}
		sightAngle 40
		view 380
		loop 1
		priority 7
		signal (| ignrAct fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance rimJet2 of Prop
	(properties
		x 52
		y 126
		description {the bubbles}
		sightAngle 40
		view 380
		loop 2
		priority 7
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance rimJet3 of Prop
	(properties
		x 117
		y 131
		description {the bubbles}
		sightAngle 40
		view 380
		loop 3
		priority 7
		signal (| ignrAct fixPriOn)
		detailLevel 4
	)
	
	(method (doVerb theVerb theItem)
		(aJet doVerb: theVerb theItem &rest)
	)
)

(instance towel of View
	(properties
		x 78
		y 109
		description {the towel}
		sightAngle 40
		approachX 80
		approachY 105
		lookStr {Behind her rests an expensive fluffy bath towel.}
		view 382
		loop 5
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance larryClothes of View
	(properties
		x 159
		y 136
		description {your leisure suit}
		lookStr {It's a good thing polyester doesn't wrinkle.}
		view 803
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbDo
					(if (== (ego view?) 381)
						(curRoom setScript: sGetDressed)
						(return TRUE)
					)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance fSpaButton of Feature
	(properties
		x 105
		y 189
		z 71
		nsTop 110
		nsLeft 98
		nsBottom 123
		nsRight 113
		description {the button}
		sightAngle 40
		approachX 101
		approachY 117
		lookStr {There's some sort of control button built into the surface of the spa's decking.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (Btst fBubblesOff)
					(Bclr fBubblesOff)
					(BubblesOn)
					(Print 380 26)
				else
					(Bset fBubblesOff)
					(BubblesOff)
					(Print 380 27)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fHotels of Feature
	(properties
		x 112
		y 49
		z 90
		nsTop 33
		nsLeft 59
		nsBottom 66
		nsRight 166
		description {the hotels}
		sightAngle 40
		approachX 84
		approachY 81
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 380 28)
				(Print 380 29)
				(Print 380 30)
				(Print 380 31)
				(Print 380 32)
				(Print 380 33)
				(Print 380 34)
				(Print 380 35)
				(Print 380 36)
				(Print 380 37)
				(Print 380 38)
			)
			(else  (Print 380 39))
		)
	)
)

(instance fStatue of Feature
	(properties
		x 112
		y 52
		nsTop 7
		nsLeft 79
		nsBottom 97
		nsRight 145
		description {the sculpture}
		sightAngle 40
		onMeCheck $1000
		approachX 126
		approachY 106
		lookStr {Look! Hooters on a stick!}
	)
)

(instance fHotTub of Feature
	(properties
		x 72
		y 146
		nsTop 124
		nsBottom 169
		nsRight 145
		description {the hot tub}
		sightAngle 40
		lookStr {The hot tub looks so warm and inviting.}
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbDo) (== theVerb verbZipper))
			(egoActions doVerb: theVerb theItem &rest)
			(return)
		)
		(switch theVerb
			(verbTalk
				(aEve doVerb: theVerb theItem &rest)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fLivingRoom of Feature
	(properties
		x 258
		y 77
		nsTop 34
		nsLeft 204
		nsBottom 121
		nsRight 312
		description {the penthouse living room}
		sightAngle 40
		lookStr {You were in that very same living room only moments ago.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fWaterfall of Feature
	(properties
		x 160
		y 192
		nsTop 130
		nsBottom 191
		nsRight 319
		description {the waterfall}
		sightAngle 40
		onMeCheck $0010
		lookStr {A lovely waterfall cascades down lava rockwork through a mass of exotic flora.}
	)
)
