;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm008 0
)

(local
	local0
	[local1 10] = [0 161 173 159 174 170 95 189 0 189]
	[local11 28] = [0 0 139 0 139 93 106 104 88 96 49 93 44 102 88 102 82 108 30 108 22 114 82 115 82 123 0 128]
	[local39 12] = [319 130 187 130 187 109 175 101 175 0 319]
	[local51 8] = [182 109 184 122 85 122 85 114]
	[local59 14] = [319 175 269 174 257 163 218 157 217 152 253 146 319 146]
	[local73 8] = [1 3037 0 7017 1 3038]
	[local81 6] = [1 3039 0 7018]
	[local87 6] = [1 3040 0 7019]
	[local93 9] = [1000 110 40 4 11 24 19 23 30]
	[local102 9] = [1003 160 50 4 11 25 23 31 31]
	[local111 9] = [1003 70 67 4 11 25 23 31 31]
)
(instance rm008 of KQ5Room
	(properties
		picture 8
		east 1
		south 7
		west 9
	)
	
	(method (init)
		(super init:)
		(= global320 123)
		(= global321 75)
		(self
			setFeatures: hole signPost path8 forest
			setRegions: 202
		)
		(theMusic number: 24 loop: -1 play:)
		(if (Btst 61)
			(log cel: 5 setPri: 8)
		else
			(log setPri: 7)
		)
		(log init:)
		(if (!= ((inventory at: 6) owner?) 200)
			(theMusic2 number: 31 loop: 1 play:)
		)
		(= global325 3035)
		(switch prevRoomNum
			(west
				(ego posn: 10 148)
				(self setScript: (ScriptID 202 1))
			)
			(east
				(ego
					illegalBits: -32768
					posn:
						(if (< (ego y?) 165) 284 else 316)
						(if (< (ego y?) 165) 144 else 180)
				)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 220 188)
				(self setScript: (ScriptID 202 1))
			)
			(215
				(ego
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(81
				(ego
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(82
				(ego
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(83
				(ego
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(84
				(ego
					init:
					normal: 0
					view: 239
					signal: 16384
					loop: 1
					cel: 0
					illegalBits: 0
					ignoreHorizon: 0
					posn: 51 105
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: backOnPath)
			)
			(19
				(ego posn: 171 101)
				(HandsOff)
				(self setScript: from19Script)
			)
			(else  (ego posn: 220 188))
		)
		(if
			(and
				(not (== prevRoomNum 215))
				(not (== prevRoomNum 81))
				(not (== prevRoomNum 82))
				(not (== prevRoomNum 83))
				(not (== prevRoomNum 84))
			)
			(ego init: view: 0 setStep: 3 2)
		)
		(poly1 points: @local1 size: 5)
		(poly2 points: @local11 size: 14)
		(poly3 points: @local39 size: 6)
		(poly4 points: @local51 size: 4)
		(poly5 points: @local59 size: 7)
		(self addObstacle: poly1 poly2 poly3 poly4 poly5)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			(
			(and (& (= temp1 (ego onControl: 0)) $0010) (Btst 61)) (HandsOff) (self setScript: enterTunnel))
			((& temp1 $4000)
				(if (not (Btst 17))
					(Bset 17)
					(HandsOff)
					(self setScript: enter19)
				else
					(theMusic fade:)
					(curRoom newRoom: 19)
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic2 fade:)
	)
)

(instance enter19 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(proc762_0 @local102 @local93 @local73 self)
			)
			(1 (ego cel: 2) (= cycles 1))
			(2 (ego cel: 3) (= cycles 1))
			(3
				(theMusic fade:)
				(curRoom newRoom: 19)
			)
		)
	)
)

(instance from19Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(proc762_1 @local93 3036 self)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 59 100 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 239
					loop: 1
					signal: 16384
					illegalBits: 0
					cel: 7
					posn: 51 105
					setCycle: Beg self
				)
			)
			(2 (curRoom newRoom: 215))
		)
	)
)

(instance backOnPath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 3))
			(1
				(if (not (Btst 61))
					(log startUpd: setCycle: End self)
					(theAudio number: 8878 loop: 1 play:)
				else
					(= cycles 1)
				)
			)
			(2
				(ego
					normal: 0
					view: 239
					cel: 0
					loop: 1
					setCycle: End self
				)
			)
			(3
				(ego
					view: 0
					normal: 1
					posn: 59 102
					setCycle: KQ5SyncWalk
					setMotion: PolyPath 84 134 self
				)
				((ego head?) show:)
			)
			(4
				(ego setMotion: 0)
				(= cycles 1)
			)
			(5
				(ego cel: 3)
				(log stopUpd:)
				(if (not (Btst 61))
					(Bset 61)
					(log setPri: 8)
					(proc762_0 @local111 @local93 @local81 self)
				else
					(= cycles 1)
				)
			)
			(6
				(ego setMotion: 0 illegalBits: -32768)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance path8 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 227)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance signPost of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 228)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== ((inventory at: 6) owner?) 200)
						(proc762_0 @local102 @local93 @local87)
					else
						(proc762_1 @local93 3040)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance forest of RFeature
	(properties
		nsBottom 95
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 229)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance log of Prop
	(properties
		x 52
		y 109
		view 239
		loop 2
		signal $4001
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 230)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 231)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hole of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0010))
					(not (& (OnControl 4 (event x?) (event y?)) $2000))
				)
				(not (Btst 61))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 232)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: enterTunnel)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)
