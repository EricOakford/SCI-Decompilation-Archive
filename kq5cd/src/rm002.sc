;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include game.sh)
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
	rm002 0
)

(local
	saveEgoView
	local1
	local2
	[pts1 12] = [
		319 48
		223 77
		03 72
		183 51
		247 0
		319
		]
	[pts2 22] = [
		107 63
		78 71
		132 90
		159 90
		180 97
		149 109
		0 121
		0 0
		238 0
		196 27
		140 55
		]
	[pts3 10] = [
		0 155
		103 155
		139 171
		140 189
		0 189
		]
	[pts4 12] = [
		319 189
		286 189
		141 125
		142 118
		239 83
		319 81
		]
	[pts5 8] = [
		138 166
		178 165
		185 173
		143 172
		]
	local67
	[local68 9] = [1003 215 40 4 11 25 23 31 31]
	[local77 9] = [1000 80 10 4 11 24 19 23 30]
	[local86 9] = [1009 235 20 5 11 24 18 24 23]
)
(instance rm002 of KQ5Room
	(properties
		picture 2
		horizon 45
		north 1
		east 29
		south 3
		west 7
	)
	
	(method (init)
		(super init:)
		(= cedricX 143)
		(= cedricY 48)
		(= global325 3023)
		(ego normal: TRUE setStep: 3 2 view: 0)
		(self
			setFeatures: mountPath room
			setRegions: 202
			addObstacle: poly1 poly2 poly3 poly4 poly5
		)
		(if (!= (theMusic number?) 24)
			(theMusic number: 24 vol: 127 loop: -1 play:)
		)
		(switch prevRoomNum
			(west
				(ego posn: 11 135)
				(self setScript: (ScriptID 202 1))
			)
			(east
				(ego posn: 311 57)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego view: 2 posn: 176 51)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 214 186)
				(self setScript: (ScriptID 202 1))
			)
			(else  (ego posn: 214 186))
		)
		(ego illegalBits: cWHITE init:)
		(if (not (Btst fSnakeGone))
			(snake cycleSpeed: 4 cel: 0 init: stopUpd:)
			(if (not (Btst fSnakeWarns))
				(Bset fSnakeWarns)
				(= local67 TRUE)
				(snake setScript: warnScript)
			)
		)
		(poly1 points: @pts1 size: 6)
		(poly2 points: @pts2 size: 11)
		(poly3 points: @pts3 size: 5)
		(poly4 points: @pts4 size: 6)
		(poly5 points: @pts5 size: 4)
	)
	
	(method (doit &tmp edge)
		(cond 
			((& (ego onControl: origin) cYELLOW)
				(ego view: 2)
			)
			((& (ego onControl: origin) cLMAGENTA)
				(ego view: 0)
			)
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
			((Btst fWearingCloak)
				(SpeakAudio 177)
				(Bclr fWearingCloak)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else
				(switch (event message?)
					(verbUse
						(if
							(and
								(== (inventory indexOf: (theIconBar curInvIcon?)) iTambourine)
								(MousedOn ego event)
							)
							(DoDisplay 0)
							(if (cast contains: snake)
								(Bset fSnakeGone)
								(SolvePuzzle 3)
								(HandsOff)
								(curRoom setScript: shakeTambourine)
							else
								(SpeakAudio 174)
							)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance warnScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(theMusic3 number: 38 loop: 1 play:)
				(proc762_1 @local77 5500 self)
			)
			(2
				(= local67 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance strike of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 27 loop: 1 play:)
				(snake loop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(snake setCycle: CycleTo 4 1)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 476
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(= seconds 2)
			)
			(3
				(= deathMessage 178)
				(EgoDead 243)
				;That wasn't wise, Graham. He who speaks with forked tongue should NEVER be trusted.
			)
		)
	)
)

(instance shakeTambourine of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 230 80 self)
			)
			(1
				(= saveEgoView (ego view?))
				((ego head?) hide:)
				(ego
					normal: 0
					view: 476
					loop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(theMusic3 number: 51 loop: -1 playBed:)
				(ego loop: 4 cycleSpeed: 0 setCycle: Forward)
				(= cycles 1)
			)
			(3 (= seconds 5))
			(4
				(theMusic4 stop:)
				(theMusic3 stop:)
				(ego loop: 3 cel: 2 cycleSpeed: 2 setCycle: BegLoop)
				(proc762_1 @local68 5501)
				(snake loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(snake dispose:)
				(ego
					normal: 1
					view: saveEgoView
					loop: 7
					cel: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(6
				(theMusic4 stop:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance mountPath of RFeature

	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (& (OnControl CMAP (event x?) (event y?)) cBLUE))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 172)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance snake of Actor
	(properties
		x 298
		y 64
		view 476
		loop 9
	)
	
	(method (doit &tmp distToSnake)
		(super doit:)
		(cond 
			((curRoom script?)
				(DoDisplay 0)
				(theMusic4 fade:)
			)
			((> (= distToSnake (ego distanceTo: self)) 70)
				(if local2
					(-- local2)
					(self cel: 0 setCycle: 0)
					(DoDisplay 0)
					(theMusic4 stop:)
				)
			)
			((< distToSnake 30)
				(DoDisplay 0)
				(HandsOff)
				(curRoom setScript: strike)
			)
			((not local2)
				(DoDisplay 0)
				(++ local2)
				(self setCycle: Forward)
				(theMusic4 number: 38 loop: -1 play:)
			)
		)
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
					(SpeakAudio 173)
					(event claimed: TRUE)
				)
				(verbDo
					(proc762_1 @local86 5502)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 176)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iTambourine
							(DoDisplay 0)
							(Bset fSnakeGone)
							(SolvePuzzle 3)
							(HandsOff)
							(curRoom setScript: shakeTambourine)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 175)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
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

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event &tmp temp0)
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
					(SpeakAudio 172)
					(event claimed: TRUE)
				)
			)
		)
	)
)
