;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm001 0
)

(local
	pts2 = [
		0 155
		0 145
		61 138
		116 138
		116 144
		98 150
		89 159
		78 161
		50 153
		]
	pts3 = [
		259 0
		258 115
		216 112
		208 106
		192 106
		166 115
		165 104
		250 27
		]
	pts4 = [
		253 0
		156 107
		139 112
		112 121
		55 129
		60 120
		0 118
		]
	pts5 = [
		319 0
		319 189
		80 189
		104 172
		304 175
		310 140
		305 122
		284 121
		285
		]
	pts6 = [
		182 125
		183 111
		187 108
		209 109
		215 113
		209 126
		214 131
		178 131
		]
	pts7 = [
		97 126
		133 126
		133 134
		97 134
		]
	pts8 = [
		29 185
		30 189
		0 189
		0 183
		]
	pts9 = [
		0 163
		32 163
		92 167
		97 172
		98 175
		76 176
		62 183
		30 181
		0 174
		]
	pts11 = [
		301 128
		303 141
		286 138
		283 125
		292 127
		]
	pts12 = [
		174 148
		173 156
		155 158
		156 148
		]
	pts13 = [
		152 145
		144 158
		131 164
		101 164
		106 151
		138 140
		]
	pts14 = [
		261 0
		282 0
		281 123
		262 118
		]
	theScript
	local157
	local158
	gEgoMoverX
	gEgoMoverY
	saveEgoView
	saveCycleSpeed
	inIntro
	local164 = [1000 93 46 4 11 24 19 23 30]
	local173 = [1 9106 0 7040 1 9107 0 7041]
	local183 = [1003 140 100 4 11 25 23 31 31]
)
(instance rm001 of KQ5Room
	(properties
		picture 1
		south 2
		west 8
	)
	
	(method (init &tmp egoX egoY temp2)
		(super init:)
		(= inIntro
			(if
				(or
					(== prevRoomNum 109)
					(== prevRoomNum 659)
					(== prevRoomNum 611)
					(== prevRoomNum 700)
				)
				(not (GameIsRestarting))
			else
				0
			)
		)
		(NormalEgo)
		(ego cycleSpeed: (ego moveSpeed?))
		(= inCartoon FALSE)
		(HandsOn)
		(Bclr 22 33)
		(= cedricX 107)
		(= cedricY 94)
		(self setRegions: 202)
		(= global325 3020)
		(if (not (ego has: iWand))
			(ego get: iWand)
		)
		(theMusic loop: -1 number: 24 play:)
		(if inIntro
			(= egoX 160)
			(= egoY 108)
		else
			(switch prevRoomNum
				(south
					(= egoX 60)
					(= egoY 187)
				)
				(east
					(= egoX 74)
					(= egoY 159)
				)
				(west
					(= egoY (ego y?))
					(= egoX 0)
					(if (< egoY 168)
						(HandsOff)
						(self setScript: enterFromTree)
					else
						(ego setMotion: MoveTo (+ (ego x?) 3) (ego y?))
					)
				)
				(76 (= egoX 169) (= egoY 98))
				(else 
					(= egoX 140)
					(= egoY 136)
				)
			)
		)
		(LoadMany VIEW 27 9 0 2)
		(ego
			view: (if (== prevRoomNum south) 0 else 2)
			posn: egoX egoY
			setStep: 2 1
			setLoop: -1
			illegalBits: (| cWHITE cYELLOW)
			init:
		)
		(door
			setPri: 1
			cel: (if (== prevRoomNum 76) (- (NumCels door) 1) else 0)
			init:
			stopUpd:
		)
		(rail1 init: stopUpd:)
		(rail2 init: stopUpd:)
		(self
			setFeatures: theWindows ornament well pond bridge smallDoor house
		)
		(if (== prevRoomNum 76)
			(HandsOff)
			(self setScript: leaveCrispins)
		)
		(if inIntro
			(HandsOff)
			(self setScript: introRoomScript)
		)
		(poly2 points: @pts2 size: 9)
		(poly3 points: @pts3 size: 8)
		(poly4 points: @pts4 size: 8)
		(poly5 points: @pts5 size: 9)
		(poly6 points: @pts6 size: 8)
		(poly7 points: @pts7 size: 4)
		(poly8 points: @pts8 size: 4)
		(poly9 points: @pts9 size: 9)
		(poly11 points: @pts11 size: 5)
		(poly12 points: @pts12 size: 4)
		(poly13 points: @pts13 size: 6)
		(poly14 points: @pts14 size: 4)
		(self
			addObstacle:
				poly2
				poly3
				poly4
				poly5
				poly6
				poly7
				poly8
				poly9
				poly11
				poly12
				poly13
				poly14
		)
		(if (== (theGame detailLevel:) 3)
			(water setCycle: Forward init:)
			(rippling setCycle: Forward init:)
			(rippling2 setCycle: Forward init:)
		)
	)
	
	(method (doit &tmp edge temp1 thisControl)
		(cond 
			((& (= thisControl (ego onControl: origin)) cCYAN)
				(ego view: 27)
			)
			((== (ego view?) 27)
				(ego view: 2)
			)
		)
		(cond 
			(script
				(script doit:)
			)
			(
				(and
					(ego edgeHit?)
					(= edge (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			((or (& thisControl cRED) (& thisControl cBLUE))
				(curRoom setScript: changeSize)
			)
			((& thisControl (| cGREY cLGREY cBROWN));$01c0
				(HandsOff)
				(self setScript: falling)
			)
			((ego inRect: 80 135 108 141)
				(ego setPri: 9)
			)
			((and (& thisControl cCYAN) (< (ego x?) 99))
				(ego setPri: 9)
			)
			(
				(and
					(& thisControl cCYAN)
					(< 99 (ego x?))
					(< (ego x?) 150)
				)
				(ego setPri: 12)
			)
			((== (ego view?) 0)
				(ego setPri: 14)
			)
			((and (& thisControl cCYAN) (> (ego x?) 149))
				(ego setPri: -1)
			)
			((& thisControl cBLACK)
				(ego setPri: -1)
			)
		)
	)
	
	(method (dispose)
		(ego illegalBits: cWHITE)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (event claimed?) (not (== (event type?) userEvent)))
			(return)
		)
	)
	
	(method (newRoom n)
		(ego setPri: -1)
		(super newRoom: n)
	)
)

(instance enterFromTree of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 47 143 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance leaveCrispins of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 160 109 self)
			)
			(1
				(door setCycle: BegLoop self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance changeSize of Script
	
	(method (changeState newState &tmp egoMover)
		(switch (= state newState)
			(0
				(= saveEgoView (ego view?))
				(= egoMover (ego mover?))
				(= saveCycleSpeed (ego cycleSpeed?))
				(if (== saveEgoView 2) (ego setPri: 1 illegalBits: 0))
				(if (IsObject egoMover)
					(= gEgoMoverX (egoMover x?))
					(= gEgoMoverY (egoMover y?))
				)
				(HandsOff)
				(rail1 setPri: 1)
				(if (== saveEgoView 2)
					(ego
						signal: (| (ego signal?) (| ignrAct fixedLoop fixPriOn))
						setMotion: PolyPath 95 181 self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 9
					normal: 0
					cycleSpeed: (ego moveSpeed?)
					setPri: -1
					setLoop: (if (== saveEgoView 2) 0 else 1)
					setStep: 4 3
					illegalBits: 0
					cel: 0
					setCycle: EndLoop self
				)
				((ego head?) hide:)
				(if (== saveEgoView 2)
					(ego setMotion: MoveTo 63 186)
				else
					(ego setMotion: MoveTo 92 174)
				)
			)
			(2
				(if (== saveEgoView 0)
					(ego
						view: 2
						setLoop: -1
						setCycle: KQ5SyncWalk
						setPri: 1
						cycleSpeed: saveCycleSpeed
						setMotion: PolyPath 100 170 self
					)
					((ego head?) show:)
				else
					(= cycles 1)
				)
			)
			(3
				(ego
					view: (if (== saveEgoView 0) 2 else 0)
					cycleSpeed: saveCycleSpeed
					cel: 0
					normal: 1
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: 12
					illegalBits: (| cWHITE cYELLOW)
					signal: (& (ego signal?) $f7ff)
				)
				((ego head?) show:)
				(if (== saveEgoView 0)
					(ego loop: 7 cel: 3 setStep: 2 1)
				else
					(ego loop: 7 cel: 2 setStep: 3 2)
				)
				(if theScript
					(ego setMotion: PolyPath local157 local158 theScript)
				else
					(if
						(and
							(not (& (OnControl CMAP gPEventX gPEventY) cBLUE))
							(not (& (OnControl CMAP gPEventX gPEventY) cRED))
							(not (& (OnControl CMAP gPEventX gPEventY) cMAGENTA))
							(not (& (OnControl CMAP gPEventX gPEventY) cLCYAN))
						)
						(ego setMotion: PolyPath gPEventX gPEventY)
					)
					(HandsOn)
				)
				(rail1 setPri: 10)
				(client setScript: 0)
			)
		)
	)
)

(instance falling of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: 90
					ignoreControl: cWHITE
					cel: 0
					setCycle: EndLoop self
					setMotion: JumpTo 196 133 self
				)
			)
			(1)
			(2
				(ego
					normal: 1
					view: 2
					setCycle: KQ5SyncWalk
					observeControl: cWHITE
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance openDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 160)
				(= local158 109)
				(= theScript self)
				(ego illegalBits: 0 setMotion: PolyPath 160 109 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				(door setCycle: EndLoop self)
			)
			(2
				(ego setMotion: MoveTo 169 98 self)
			)
			(3
				(Bset fEnteredCrispinHouse)
				(curRoom newRoom: 76)
			)
		)
	)
)

(instance lookWell of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 253)
				(= local158 118)
				(= theScript self)
				(ego setMotion: PolyPath 253 118 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				(ego loop: 7 cel: 3)
				(= cycles 2)
			)
			(2
				(SpeakAudio 162)
				(= cycles 1)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance getDrink of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local157 188)
				(= local158 147)
				(= theScript self)
				(ego setMotion: PolyPath 188 147 self)
			)
			(1
				(= local157 0)
				(= local158 0)
				(= theScript 0)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 45
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2
				(ego loop: (+ (ego loop?) 4) cel: 0 setCycle: EndLoop self)
			)
			(3 (= cycles 20))
			(4
				(ego cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego loop: (- (ego loop?) 4) cel: 3 setCycle: BegLoop self)
			)
			(6
				(SpeakAudio 9070)
				(ego
					normal: 1
					view: 2
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance rail1 of Prop
	(properties
		x 94
		y 156
		view 770
		loop 4
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance rail2 of Prop
	(properties
		x 123
		y 161
		view 770
		loop 4
		cel 1
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance door of Door
	(properties
		x 170
		y 96
		view 770
		loop 5
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 163)
					(event claimed: TRUE)
				)
				(verbDo
					(if (Btst fEnteredCrispinHouse)
						(SpeakAudio 171)
					else
						(HandsOff)
						(ego setScript: openDoor)
					)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance theWindows of RFeature
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cLBLUE))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 164)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance house of RFeature
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cGREEN))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 165)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance well of RFeature
	(properties
		nsTop 89
		nsLeft 246
		nsBottom 109
		nsRight 264
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 166)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(ego setScript: lookWell)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance ornament of RFeature
	(properties
		nsTop 95
		nsLeft 105
		nsBottom 128
		nsRight 125
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 167)
					(event claimed: TRUE)
				)
				(verbDo
					(proc762_1 @local164 3021)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance pond of RFeature

	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cCYAN))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 168)
					(event claimed: TRUE)
				)
				(verbDo
					(HandsOff)
					(ego setScript: getDrink)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance smallDoor of RFeature
	(properties
		nsTop 111
		nsLeft 186
		nsBottom 129
		nsRight 209
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 169)
					(event claimed: TRUE)
				)
				(verbDo
					(proc762_1 @local164 3022)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance bridge of RFeature
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cLRED))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 170)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance water of Prop
	(properties
		x 256
		y 154
		view 770
		priority 1
		signal fixPriOn
		cycleSpeed 4
	)
)

(instance rippling of Prop
	(properties
		x 158
		y 168
		view 770
		loop 1
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
)

(instance rippling2 of Prop
	(properties
		x 79
		y 154
		view 770
		loop 2
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 2
	)
)

(instance poly2 of Polygon
	(properties
		type PTotalAccess
	)
)

(instance poly3 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly4 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly5 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly6 of Polygon
	(properties
		type PTotalAccess
	)
)

(instance poly7 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly8 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly9 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly11 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly12 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly13 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly14 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance introRoomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (- (ego x?) 1) (+ (ego y?) 1))
				(= cycles 15)
			)
			(1
				(ego
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: -1
					setMotion: MoveTo 140 136 self
				)
			)
			(2
				(proc762_0 @local183 @local164 @local173 self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
