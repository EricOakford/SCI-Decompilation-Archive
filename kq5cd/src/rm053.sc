;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm053 0
)

(local
	local0
	[local1 2]
	[local3 16] = [0 189 0 0 43 0 43 136 59 153 77 163 128 168 76 189]
	[local19 20] = [137 183 115 183 136 166 117 162 111 156 86 156 61 148 62 0 164 0 169 153]
	[local39 9] = [1000 125 54 4 11 24 19 23 30]
	[local48 9] = [1003 122 112 4 11 25 23 31 31]
	[local57 6] = [0 3085 1 7032]
)
(instance rm053 of KQ5Room
	(properties
		picture 53
		horizon 145
		north 54
		south 52
	)
	
	(method (init)
		(super init:)
		(theMusic number: 834 loop: -1 play:)
		(if (not (Btst 55))
			(= cedricX 159)
			(= cedricY 109)
			(self setRegions: 202)
			(= global325 3083)
		)
		(self
			setFeatures: chasm stairWay aCastle path1 path2 path3
			obstacles: polyListTop
		)
		(ego init:)
		(switch prevRoomNum
			(north
				(ego view: 2 observeControl: 16384 posn: 57 146)
				(= local0 1)
				(if (not (Btst 55))
					(globalCedric
						view: 138
						loop: 2
						cel: 0
						cycleSpeed: 3
						setCycle: EndLoop
					)
				)
			)
			(else 
				(ego
					view: 0
					ignoreControl: 16384
					setPri: 15
					setStep: 3 2
					posn: 156 185
				)
				(= local0 0)
				(walkInCode doit:)
			)
		)
		(poly1 points: @local3 size: 8)
		(poly2 points: @local19 size: 10)
		(polyListTop add: poly1 poly2)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (not (Btst 55))
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: temp0)
				)
			)
			(
				(and
					(& (= temp1 (ego onControl: 0)) $0080)
					(!= (ego view?) 9)
				)
				(self setScript: goingUp)
			)
			((and (& temp1 $0100) (!= (ego view?) 9)) (self setScript: goingDown))
			((& temp1 $0400)
				(ego
					priority: 15
					signal: (| (ego signal?) $0010)
					ignoreControl: 16384
				)
				(= local0 0)
			)
			((and (& temp1 $2000) (== local0 1)) (HandsOff) (self setScript: fallBottom))
			((& temp1 $0002)
				(= local0 1)
				(ego
					priority: -1
					signal: (& (ego signal?) $ffef)
					observeControl: 16384
				)
			)
			((and (& temp1 $1000) (== local0 0)) (HandsOff) (self setScript: fallTop))
		)
	)
	
	(method (dispose)
		(polyListTop dispose:)
		(ego ignoreControl: 16384)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(if (== n 52) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance fallTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 68)
				(if (not (Btst 55))
					(proc762_1 @local39 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(theAudio number: 7053 loop: 1 play:)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 68
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
					setPri: 13
					illegalBits: 0
				)
			)
			(2
				(ego yStep: 8 setMotion: MoveTo (ego x?) 230 self)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance fallBottom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 70)
				(if (not (Btst 55))
					(proc762_1 @local39 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(theAudio number: 7053 loop: 1 play:)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 70
					setLoop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
					setPri: 13
					illegalBits: 0
				)
			)
			(2
				(ego yStep: 8 setMotion: MoveTo (ego x?) 230 self)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance goingDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 9
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
					setMotion: PolyPath 131 164 self
				)
			)
			(1
				((ego head?) show:)
				(ego
					normal: 1
					view: 2
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 123 166 self
				)
			)
			(2
				(ego signal: 8194 setMotion: MoveTo 103 160 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance goingUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 123 169 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 9
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
					setMotion: PolyPath 101 188 self
				)
			)
			(2
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 98 186 self
				)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance conversation of Script
	(properties)
	
	(method (doit)
		(if (== (curRoom script?) fallTop)
			(self dispose:)
		else
			(super doit: &rest)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 6)
				(Bset 98)
			)
			(1
				(proc762_0 @local39 @local48 @local57 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance chasm of RFeature
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
					(SpeakAudio 562)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance stairWay of RFeature
	(properties
		nsTop 168
		nsLeft 90
		nsBottom 181
		nsRight 124
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
					(SpeakAudio 563)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance aCastle of RFeature
	(properties
		nsLeft 77
		nsBottom 124
		nsRight 319
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
					(SpeakAudio 564)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path1 of RFeature
	(properties
		nsTop 141
		nsLeft 44
		nsBottom 166
		nsRight 138
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
					(SpeakAudio 563)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path2 of RFeature
	(properties
		nsTop 150
		nsLeft 157
		nsBottom 189
		nsRight 319
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
					(SpeakAudio 562)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path3 of RFeature
	(properties
		nsTop 114
		nsLeft 168
		nsBottom 131
		nsRight 273
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
					(SpeakAudio 562)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 565)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance polyListTop of List
	(properties)
)

(instance poly1 of Polygon
	(properties
		type $0000
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance walkInCode of CodeCue
	(properties)
	
	(method (doit)
		(if (not (Btst 55))
			(curRoom setScript: (ScriptID 202 1) self)
		)
	)
	
	(method (cue)
		(if (not (Btst 98)) (curRoom setScript: conversation))
	)
)
