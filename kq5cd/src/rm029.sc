;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use System)

(public
	rm029 0
)

(local
	local0
	local1
	[local2 32] = [0 162 146 119 223 79 232 60 196 58 192 58 119 55 112 51 117 47 229 38 252 41 286 32 273 0 319 0 319 189 0 189]
	[local34 30] = [0 0 257 0 268 28 190 28 181 35 120 39 99 51 107 62 189 62 190 69 144 81 149 88 121 103 57 119 0 127]
	[local64 9] = [1000 90 40 4 11 24 19 23 30]
)
(instance rm029 of KQ5Room
	(properties
		picture 29
		north 30
		west 2
	)
	
	(method (init)
		(super init:)
		(= cedricX 119)
		(= cedricY 63)
		(= global325 3057)
		(self setFeatures: path29 cliffs setRegions: 202)
		(switch prevRoomNum
			(west
				(ego view: 2 posn: 6 151)
				(= local0 1)
				(self setScript: (ScriptID 202 1))
				(theMusic number: 5 loop: -1 play:)
			)
			(north
				(ego
					normal: 1
					view: (if (Btst 15) 14 else 108)
					posn: 261 35
				)
				(++ local1)
				(self setScript: (ScriptID 202 1))
			)
			(else 
				(theMusic number: 5 loop: -1 play:)
				(ego view: 2 posn: 6 151)
				(= local0 1)
			)
		)
		(ego setStep: 2 1 init:)
		(poly1 points: @local2 size: 16)
		(poly3 points: @local34 size: 15)
		(self addObstacle: poly1 poly3)
	)
	
	(method (doit &tmp temp0 temp1 egoView)
		(cond 
			(script (script doit:))
			((not local1) (++ local1) (ego setScript: timedMess))
			((& (= temp1 (ego onControl: 1)) $0004)
				((ScriptID 202 2) register: 1)
				(self setScript: (ScriptID 202 2))
			)
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(theMusic fade:)
				(self setScript: (ScriptID 202 2))
			)
			(
				(and
					(not (Btst 15))
					(not (== (= egoView (ego view?)) 106))
					(not (== egoView 108))
					(> (ego x?) 20)
				)
				(SpeakAudio 410)
				((ego head?) hide:)
				(ego view: 106)
			)
			((& (ego onControl: 0) $2000) (self setScript: falling))
			((& temp1 $1000)
				(if (Btst 15)
					(ego view: 14)
					((ego head?) show:)
				else
					(ego view: 108)
					((ego head?) hide:)
				)
			)
			((& temp1 $4000)
				(ego normal: 1)
				(if (Btst 15) (ego view: 12) else (ego view: 106))
				((ego head?) show:)
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
			(else
				(switch (event message?)
					(JOY_DOWNRIGHT
						(if (MousedOn ego event)
							(switch (inventory indexOf: (theIconBar curInvIcon?))
								(26
									(if (not (Btst 15))
										(if (not (Btst 48)) (Bset 48) (SolvePuzzle 4))
										(theMusic2 fade:)
										(Bset 15)
										(SpeakAudio 415)
										(ego view: (if (== (ego view?) 108) 14 else 12))
										((ego head?) show:)
										(event claimed: 1)
									)
								)
								(28 (event claimed: 0))
								(else 
									(if (not (Btst 15))
										(SpeakAudio 416)
										(event claimed: 1)
									)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance timedMess of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SpeakAudio 411 0 1)
				(DoDisplay (Format @global185 29 0))
				(= seconds 30)
			)
			(1
				(DoDisplay 0)
				(client setScript: 0)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(proc762_1 @local64 3058 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view:
						(cond 
							((== (ego view?) 2) (if (Btst 15) 78 else 70))
							((Btst 15) 80)
							(else 72)
						)
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
					setPri: (if (< (ego y?) 100) 1 else (ego priority?))
					illegalBits: 0
				)
				(theAudio number: 7053 play:)
				(theMusic3 number: 83 loop: 1 vol: 127 priority: 15 play:)
			)
			(2
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance path29 of RFeature
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
					(SpeakAudio 413)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cliffs of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 414)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0000
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)
