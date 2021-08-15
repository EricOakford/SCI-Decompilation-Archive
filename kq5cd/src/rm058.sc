;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include sci.sh)
(use Main)
(use castle)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm058 0
)

(local
	local0
	local1
	local2
	[local3 16] = [0 136 50 136 38 169 278 168 275 140 319 137 319 189 0 189]
	[local19 36] = [0 0 319 0 319 124 270 125 266 115 249 114 242 107 232 112 217 104 207 115 111 114 97 105 87 111 77 105 70 115 56 117 52 125 0 126]
)
(instance rm058 of KQ5Room
	(properties
		picture 58
		east 59
		west 57
	)
	
	(method (init)
		(super init:)
		(ego view: 34 init: setPri: 10)
		((ego head?) setPri: (ego priority?))
		(= global345 260)
		(= global346 140)
		(= magicDoorX 159)
		(= magicDoorY 116)
		(Load rsVIEW 34)
		(Load rsVIEW 692)
		(Load rsVIEW 906)
		(self
			setFeatures: organ angel doorWay1 doorWay2
			setRegions: 550
			addObstacle: poly1 poly2
		)
		(switch prevRoomNum
			(west
				(curRoom setScript: enterLeft)
			)
			(else 
				(curRoom setScript: enterRight)
			)
		)
		(rightSnake init: stopUpd:)
		(leftSnake init: stopUpd:)
		(evilMask init:)
		(cond 
			((== henchmanState 3) ((ScriptID 550 3) init:))
			(
				(and
					(not henchmanTimer)
					(not wizardTimer)
					(> (Random 0 100) 50)
				)
				((ScriptID 550 3) init:)
			)
		)
		(poly1 points: @local3 size: 8)
		(poly2 points: @local19 size: 18)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
			(and (== wizardState 3) (ego inRect: 131 95 210 198))
				(if (Random 0 1)
					(= wizardX 78)
					(= wizardY 130)
					(= wizardAngle 270)
					(= global354 90)
				else
					(= wizardX 246)
					(= wizardY 125)
					(= wizardAngle 90)
					(= global354 270)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			((ego inRect: -10 111 35 140) (curRoom setScript: exitLeft))
			((ego inRect: 286 123 330 145) (evilMask addToPic:) (curRoom setScript: exitRight))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance rightSnake of Prop
	(properties
		x 96
		y 8
		view 692
		loop 1
		detailLevel 3
	)
)

(instance leftSnake of Prop
	(properties
		x 215
		y 7
		view 692
		loop 2
		detailLevel 3
	)
)

(instance evilMask of Prop
	(properties
		x 160
		y 17
		view 692
		loop 3
		detailLevel 3
	)
	
	(method (doit)
		(super doit:)
		(if (and (< 145 (ego x?)) (< (ego x?) 175))
			(self loop: 4 cel: (/ (- 154 (ego y?)) 14))
		else
			(self loop: 3 cel: (/ (ego x?) 35))
		)
	)
)

(instance keys1 of Prop
	(properties
		x 141
		y 89
		view 692
	)
)

(instance keys2 of Prop
	(properties
		x 173
		y 89
		view 692
	)
)

(instance keys3 of Prop
	(properties
		x 158
		y 93
		view 692
	)
)

(instance organ of RFeature
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
					(SpeakAudio 617)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cond 
						(local2 (event claimed: 0))
						((and (not local0) (not (curRoom script?)))
							(++ local2)
							(if (== henchmanState 1)
								((ScriptID 550 3) setScript: 0 setMotion: 0)
							)
							(evilMask setScript: playOrgan)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance angel of RFeature
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
					(SpeakAudio 618)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 621)
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
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 619)
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
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 620)
					(event claimed: 1)
				)
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
					illegalBits: 0
					posn: -4 130
					setMotion: MoveTo 42 130 self
				)
			)
			(1
				(CastleHandsOn)
				(self dispose:)
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
					illegalBits: 0
					posn: 317 130
					setMotion: MoveTo 280 130 self
				)
			)
			(1
				(CastleHandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitRight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(if local0 (theMusic fade:))
				(ego illegalBits: 0 setMotion: MoveTo 317 130 self)
			)
			(1
				(if local0 (= seconds 3) else (= cycles 1))
			)
			(2
				(ego view: 0)
				(= henchmanState 0)
				(if local0 (= wizardTimer 10))
				(curRoom newRoom: 59)
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
				(if (!= henchmanState 3) (theMusic fade:))
				(ego illegalBits: 0 setMotion: MoveTo -4 130 self)
			)
			(1
				(if local0 (= seconds 2) else (= cycles 1))
			)
			(2
				(if (or (< henchmanState 3) (== henchmanState 7))
					(= henchmanState 0)
					(ego view: 0)
					(if local0 (= wizardTimer 10))
					(curRoom newRoom: 57)
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

(instance playOrgan of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (== henchmanState 2)
					(SpeakAudio 616)
				else
					(CastleHandsOff)
					(= local0 1)
					(theMusic fade:)
					(ego setMotion: PolyPath 158 114 self)
				)
			)
			(1
				(Face ego evilMask 5)
				(RedrawCast)
				(SpeakAudio 622)
				(theMusic number: 128 loop: 1 play: self)
				(leftSnake
					setCycle: (if (== (theGame detailLevel:) 3) Fwd else 0)
					cycleSpeed: 5
				)
				(rightSnake
					setCycle: (if (== (theGame detailLevel:) 3) Fwd else 0)
					cycleSpeed: 5
				)
				(keys1
					init:
					cycleSpeed: (Random 2 8)
					setCycle: (if (> (theGame detailLevel:) 1) Fwd else 0)
					setCycle: Fwd
				)
				(keys2
					init:
					cycleSpeed: (Random 2 8)
					setCycle: (if (> (theGame detailLevel:) 1) Fwd else 0)
				)
				(keys3
					init:
					cycleSpeed: (Random 2 8)
					setCycle: (if (> (theGame detailLevel:) 1) Fwd else 0)
				)
				(CastleHandsOn)
			)
			(2
				(leftSnake stopUpd:)
				(rightSnake stopUpd:)
				(keys1 hide: stopUpd:)
				(keys2 hide: stopUpd:)
				(keys3 hide: stopUpd:)
				(if (or (== henchmanState 3) (== henchmanState 2))
					((ScriptID 550 3) setScript: 0 setMotion: 0 stopUpd:)
				)
				(if (!= henchmanState 3) (= wizardTimer 3))
				(client setScript: 0)
			)
		)
	)
)
