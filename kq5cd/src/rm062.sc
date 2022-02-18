;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include sci.sh)
(use Main)
(use castle)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm062 0
)

(local
	[local0 12] = [0 189 0 143 51 142 35 173 253 173 253 189]
	[local12 8] = [319 189 277 189 267 150 319 150]
	[local20 32] = [0 0 319 0 319 126 266 127 261 119 246 117 233 102 196 102 184 119 131 119 119 104 80 104 68 116 52 119 52 127 0 127]
)
(instance rm062 of KQ5Room
	(properties
		picture 62
		east 65
		south 61
		west 63
	)
	
	(method (init)
		(super init:)
		(= henchmanState 0)
		(Load rsVIEW 34)
		(self
			setFeatures: stairs skull doorWay1 doorWay2 theWindows
			setRegions: 550
			addObstacle: poly1 poly2 poly3
		)
		(switch prevRoomNum
			(south
				(curRoom setScript: goUpstairs)
			)
			(east
				(curRoom setScript: enterRight)
			)
			(else 
				(curRoom setScript: enterLeft)
			)
		)
		(glow1 init: setCycle: Forward)
		(ego init:)
		(poly1 points: @local0 size: 6)
		(poly2 points: @local12 size: 4)
		(poly3 points: @local20 size: 16)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
			(and (== wizardState 3) (ego inRect: 109 128 207 159))
				(if (Random 0 1)
					(= wizardX 45)
					(= wizardY 129)
					(= wizardAngle 270)
					(= global354 90)
				else
					(= wizardX 245)
					(= wizardY 125)
					(= wizardAngle 90)
					(= global354 270)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			((ego inRect: -4 127 34 145) (curRoom setScript: exitLeft))
			((ego inRect: 278 126 322 145) (curRoom setScript: exitRight))
			((& (ego onControl:) $4000) (curRoom setScript: goDownstairs))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance glow1 of Prop
	(properties
		x 158
		y 83
		view 700
		cycleSpeed 4
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance skull of RFeature
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
					(SpeakAudio 642)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 647)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance theWindows of RFeature
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
					(SpeakAudio 643)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay1 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 644)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay2 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 645)
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

(instance goDownstairs of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					blocks: 0
					setMotion: MoveTo 271 255 self
				)
			)
			(1 (curRoom newRoom: 61))
		)
	)
)

(instance goUpstairs of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					init:
					view: 34
					illegalBits: 0
					posn: 271 255
					setMotion: MoveTo 262 175 self
				)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					init:
					view: 34
					posn: 358 139
					setMotion: MoveTo 276 139 self
				)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego
					init:
					view: 34
					posn: -2 139
					setMotion: MoveTo 43 139 self
				)
			)
			(1
				(CastleHandsOn)
				(= global359 0)
				(client setScript: 0)
			)
		)
	)
)

(instance exitLeft of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego setMotion: MoveTo -2 139 self)
			)
			(1 (curRoom newRoom: 63))
		)
	)
)

(instance exitRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego setMotion: MoveTo 358 139 self)
			)
			(1
				(theMusic fade:)
				(curRoom newRoom: 65)
			)
		)
	)
)

(instance stairs of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 646)
					(event claimed: 1)
				)
			)
		)
	)
)
