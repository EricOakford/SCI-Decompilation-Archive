;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include sci.sh)
(use Main)
(use Intrface)
(use castle)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm064 0
)

(local
	local0
	local1
	[local2 56] = [147 0 148 138 64 138 36 155 35 164 15 167 22 182 62 184 128 184 150 187 304 183 305 162 301 159 286 157 269 152 260 149 268 143 265 138 258 138 249 146 207 146 199 138 173 138 162 0 319 0 319 189 0 189]
)
(instance rm064 of KQ5Room
	(properties
		picture 64
		horizon 135
		north 63
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 706)
		(Load rsVIEW 710)
		(= global357 274)
		(= global358 166)
		(= global355 79)
		(= global356 156)
		(self
			setRegions: 550
			setFeatures: desk shelves doorWay room
			addObstacle: poly1
			setScript: enterNorth
		)
		(glow1 setCycle: Fwd init:)
		(eyeBall init: stopUpd:)
		(book init: stopUpd:)
		(poly1 points: @local2 size: 28)
		(switch wizardState
			(5
				((ScriptID 550 7) init: setScript: wizSleepingScript)
			)
			(7
				(= wizardTimer 2)
				(= global359 0)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
			(and (ego inRect: 77 147 260 171) (== wizardState 3))
				(if (< (ego x?) 158)
					(= wizardX 263)
					(= wizardY 176)
					(= wizardAngle 315)
					(= global354 135)
				else
					(= wizardX 72)
					(= wizardY 145)
					(= wizardAngle 135)
					(= global354 225)
				)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			(
				(and
					(ego edgeHit?)
					(== 63 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (and (== wizardState 5) (== catState 6))
					(= wizardState 6)
					(= henchmanTimer 0)
					(= wizardTimer 0)
					(= global359 0)
				)
				(curRoom newRoom: 63)
			)
			(
				(and
					global359
					(not (Btst 64))
					(!= local1 (GetTime 1))
				)
				(= local1 (GetTime 1))
				(if (and (not (-- global359)) (== wizardState 0))
					(= wizardState 5)
					((ScriptID 550 7) init: setScript: zzzScript)
				)
			)
		)
		(if (not local0)
			(if (> (ego y?) 175)
				(ego setPri: 13)
			else
				(ego setPri: -1)
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
)

(instance glow1 of Prop
	(properties
		x 54
		y 153
		view 710
		loop 2
		priority 15
		signal $4010
		cycleSpeed 4
		detailLevel 3
	)
)

(instance eyeBall of Prop
	(properties
		x 159
		y 28
		view 710
		loop 6
		cel 2
		priority 15
		signal $4010
		detailLevel 3
	)
	
	(method (doit &tmp eyeBallLoop eyeBallCel)
		(super doit:)
		(if (>= (theGame detailLevel:) detailLevel)
			(= eyeBallCel (eyeBall cel?))
			(= eyeBallLoop (eyeBall loop?))
			(cond 
				(
					(and
						(== eyeBallCel 2)
						(== eyeBallLoop 6)
						(> (ego y?) 160)
					)
					(eyeBall setCycle: Beg self)
				)
				(
					(and
						(== eyeBallCel 0)
						(== eyeBallLoop 6)
						(> (ego y?) 160)
					)
					(eyeBall loop: 5)
				)
				((== eyeBallLoop 5)
					(if (< (ego y?) 160)
						(eyeBall cel: 0 loop: 6 setCycle: End)
					else
						(eyeBall cel: (/ (ego x?) 70))
					)
				)
			)
		)
	)
)

(instance shelves of RFeature
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
					(SpeakAudio 658)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 663)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay of RFeature
	(properties
		nsTop 52
		nsLeft 137
		nsBottom 137
		nsRight 185
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 659)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 660)
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

(instance enterNorth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				(ego posn: 158 130 init: setMotion: PolyPath 158 142 self)
			)
			(1
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance book of Prop
	(properties
		x 110
		y 179
		view 710
		loop 3
		cel 1
		priority 14
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (not (Btst 21))
						(SpeakAudio 661)
					else
						(SpeakAudio 662)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not (Btst 21))
						(Bset 21)
						(CastleHandsOff)
						(curRoom setScript: lookBook)
					else
						(SpeakAudio 664)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance lookBook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoAudio 1 7068)
				(= register 4)
				(= local0 1)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 110 179 self
				)
			)
			(1
				((ego head?) hide:)
				(book hide:)
				(-- register)
				(theAudio number: 7068 loop: 1 play:)
				(ego
					normal: 0
					view: 710
					setLoop: 0
					cycleSpeed: 2
					cel: 0
					setPri: 15
					setCycle: End self
				)
				(if register (-- state))
			)
			(2
				(ego setLoop: 1 cel: 0 setCycle: Fwd cycleSpeed: 12)
				(= seconds 3)
			)
			(3
				(cast eachElementDo: #hide)
				(curRoom drawPic: 219)
				(SolvePuzzle 3)
				(SpeakAudio 665 self)
			)
			(4
				(cast eachElementDo: #show)
				(curRoom drawPic: 64)
				(ego
					view: 0
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setLoop: -1
					setPri: 13
					posn: 105 180
					ignoreActors: 0
					illegalBits: -32768
				)
				(= cycles 5)
			)
			(5
				(Bset 21)
				(CastleHandsOn)
				(= local0 0)
				(client setScript: 0)
			)
		)
	)
)

(instance zzzScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(CastleHandsOff)
				((ScriptID 550 7)
					view: 706
					setLoop: 8
					posn: 159 105
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(1 (= cycles (Random 5 10)))
			(2
				((ScriptID 550 7) setLoop: 7 cel: 0 setCycle: End self)
			)
			(3 (= cycles (Random 5 10)))
			(4
				((ScriptID 550 7)
					setLoop: 6
					setCel: 255
					setCycle: Beg self
				)
			)
			(5
				(= henchmanTimer 180)
				(CastleHandsOn)
				((ScriptID 550 7) setLoop: 5)
				(= cycles 1)
			)
			(6
				(if (not (Random 0 3))
					(if (Random 0 1)
						((ScriptID 550 7) setCycle: End)
					else
						((ScriptID 550 7) setCycle: Beg)
					)
				)
				(= seconds (Random 2 6))
				(= state 5)
				(if (not henchmanTimer) (= state 6))
			)
			(7
				(CastleHandsOff)
				((ScriptID 550 7) setLoop: 6 cel: 0 setCycle: End self)
			)
			(8 (= cycles (Random 5 10)))
			(9
				((ScriptID 550 7) setLoop: 7 cel: 7 setCycle: End self)
			)
			(10 (= cycles (Random 5 10)))
			(11
				((ScriptID 550 7) setLoop: 8 cel: 11 setCycle: Beg self)
			)
			(12
				(CastleHandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance wizSleepingScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID 550 7)
					view: 706
					setLoop: 5
					posn: 159 105
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(1
				(if (not (Random 0 3))
					(if (Random 0 1)
						((ScriptID 550 7) setCycle: End)
					else
						((ScriptID 550 7) setCycle: Beg)
					)
				)
				(= seconds (Random 2 6))
				(= state 0)
			)
		)
	)
)

(instance wakeUpScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID 550 7) setLoop: 6 cel: 0 setCycle: End self)
			)
			(1
				((ScriptID 550 7)
					setLoop: 7
					setCel: 255
					setCycle: Beg self
				)
			)
			(2
				((ScriptID 550 7)
					setLoop: 8
					setCel: 255
					setCycle: Beg self
				)
			)
			(3
				((ScriptID 550 7) posn: 1000 1000)
			)
		)
	)
)

(instance desk of RFeature
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
					(if (not (Btst 21))
						(SpeakAudio 661)
					else
						(SpeakAudio 662)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 666)
					(event claimed: 1)
				)
			)
		)
	)
)
