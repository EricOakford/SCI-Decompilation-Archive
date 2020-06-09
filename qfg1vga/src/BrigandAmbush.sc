;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh) (include "91.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm91 0
)

(local
	brigandsBehindLog
	local1
	[arrowPosn 108]
	theArrowID
	local111
	local112 =  30
	local113 =  20
	theNewView
	gEgoLoop
	theNewView_2
	[newView 28]
	local145
	local146
	local147
	local148 =  1
	local149
	egoKilled
	local151
	logJumps
)
(procedure (localproc_00b0 param1 param2 param3 &tmp temp0)
	(= local146 0)
	(= local145 1)
	(if (< (= temp0 (Abs (- param1 param2))) 2)
		(= local145 (if (== temp0 param3) 0 else 2))
	)
	(if
	(and (< param1 2) (< param2 2) (!= param1 param3))
		(= local146 (if (== param2 0) 2 else -2))
	)
)

(procedure (localproc_0100 param1 param2 param3 param4 &tmp [temp0 2])
	(if (localproc_0120 param1 param2 param3 param4)
	else
		(localproc_0230 param1 param2 param3 param4)
	)
)

(procedure (localproc_0120 param1 param2 param3 param4 &tmp temp0 temp1)
	(= temp0
		(localproc_0317
			param1
			param2
			(ego x?)
			(- (ego y?) 35)
			(ego x?)
			(- (ego y?) 15)
		)
	)
	(if
		(==
			temp0
			(= temp1
				(localproc_0317
					param3
					param4
					(ego x?)
					(- (ego y?) 35)
					(ego x?)
					(- (ego y?) 15)
				)
			)
		)
		(return FALSE)
	)
	(= temp0
		(localproc_0317
			(ego x?)
			(- (ego y?) 35)
			param1
			param2
			param3
			param4
		)
	)
	(return
		(if
			(==
				temp0
				(= temp1
					(localproc_0317
						(ego x?)
						(- (ego y?) 15)
						param1
						param2
						param3
						param4
					)
				)
			)
			(return FALSE)
		else
			(= theNewView
				(-
					(/
						(+
							(* param4 (ego x?))
							(- (* param4 param1))
							(- (* param2 (ego x?)))
							(* param2 param3)
						)
						(- param3 param1)
					)
					(ego y?)
				)
			)
			(= gEgoLoop (ego loop?))
			(if (< (ego x?) param1)
				(= theNewView_2 1)
			else
				(= theNewView_2 0)
			)
			(return TRUE)
		)
	)
)

(procedure (localproc_0230 param1 param2 param3 param4 &tmp temp0 temp1)
	(= temp0
		(localproc_0317
			param1
			param2
			(- (ego y?) 25)
			(- (ego x?) 8)
			(- (ego y?) 25)
			(+ (ego x?) 8)
		)
	)
	(if
		(==
			temp0
			(= temp1
				(localproc_0317
					param3
					param4
					(- (ego y?) 25)
					(- (ego x?) 8)
					(- (ego y?) 25)
					(+ (ego x?) 8)
				)
			)
		)
		(return FALSE)
	)
	(= temp0
		(localproc_0317
			(- (ego y?) 25)
			(- (ego x?) 8)
			param1
			param2
			param3
			param4
		)
	)
	(return
		(if
			(==
				temp0
				(= temp1
					(localproc_0317
						(- (ego y?) 25)
						(+ (ego x?) 8)
						param1
						param2
						param3
						param4
					)
				)
			)
			(return FALSE)
		else
			(= theNewView (- -15 (Random 0 20)))
			(= gEgoLoop (ego loop?))
			(return TRUE)
		)
	)
)

(procedure (localproc_0317 param1 param2 param3 param4 param5 param6)
	(return
		(if
			(>
				(+
					(* param6 param3)
					(- (* param5 param4))
					(* param1 (- param4 param6))
					(* param2 (- param5 param3))
				)
				0
			)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (localproc_0347 param1 param2 param3 &tmp temp0 temp1 temp2 temp3)
	(if (< param1 (ego x?))
		(= temp1 (+ (- (ego x?) param1) (Random 0 35)))
	else
		(= temp1 (- (- (ego x?) param1) (Random 0 35)))
	)
	(= temp2 (- (ego y?) param2))
	(= temp3 (/ (* temp1 2) 128))
	(= temp1 (/ temp1 8))
	(= temp2 (/ temp2 8))
	(= [arrowPosn param3] param1)
	(= [arrowPosn (+ param3 9)] param2)
	(= temp0 1)
	(while (<= temp0 4)
		(= [arrowPosn (+ param3 temp0)]
			(+ [arrowPosn (+ param3 temp0 -1)] temp1)
		)
		(= [arrowPosn (+ param3 temp0 9)]
			(+ [arrowPosn (+ param3 temp0 8)] (- temp2 temp3))
		)
		(++ temp0)
	)
	(= temp0 5)
	(while (<= temp0 8)
		(= [arrowPosn (+ param3 temp0)]
			(+ [arrowPosn (+ param3 temp0 -1)] temp1)
		)
		(= [arrowPosn (+ param3 temp0 9)]
			(+ [arrowPosn (+ param3 temp0 8)] temp2 temp3)
		)
		(++ temp0)
	)
)

(instance rm91 of Room
	(properties
		picture 91
		style DISSOLVE
		horizon 92
		north 93
		east 92
	)
	
	(method (init)
		(LoadMany RES_VIEW 91 90 400)
		(Load RES_SOUND 73 31)
		(mouseDownHandler add: self)
		(super init:)
		(self setFeatures: theLog)
		(NormalEgo)
		(cSound stop:)
		(brigandS number: 73 init: play:)
		(archer1 init: stopUpd:)
		(archer2 init: stopUpd:)
		(archer3 init: stopUpd:)
		(archer4 init: stopUpd:)
		(archer5 init: stopUpd:)
		(switch prevRoomNum
			(93
				(= local149 0)
				(Bclr BRIGANDS_BEHIND_LOG)
				(ego
					posn: 122 95
					setPri: 7
					setHeading: 270
					init:
					setMotion: MoveTo 127 110 egoEnters
				)
				(= disabledActions (| disabledActions ACTION_REST))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								319
								189
								0
								189
								0
								0
								123
								72
								84
								87
								88
								121
								106
								126
								121
								122
								139
								116
								139
								105
								132
								69
								131
								0
								319
								0
							yourself:
						)
				)
			)
			(vBrigand
				(= local149 0)
				(ego
					posn: 122 95
					setPri: 7
					setHeading: 270
					init:
					ignoreActors:
					setMotion: PolyPath 117 90
				)
				(= disabledActions (| disabledActions ACTION_REST))
				(stiff1 init: stopUpd:)
				(stiff2 init: stopUpd:)
				(stiff3 init: stopUpd:)
			)
			(else 
				(self setScript: egoEnters)
				(Bset BRIGANDS_BEHIND_LOG)
				(= brigandsBehindLog TRUE)
				(= local149 1)
				(spearman4 init: stopUpd:)
				(spearman5 init: stopUpd:)
				(ego
					posn: 309 148
					setPri: 11
					init:
					setMotion: PolyPath 264 146 egoEnters
				)
				(= disabledActions (| disabledActions ACTION_REST))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								0
								0
								319
								0
								319
								132
								271
								133
								261
								129
								239
								131
								183
								125
								170
								121
								155
								124
								123
								124
								108
								130
								97
								127
								89
								132
								94
								137
								241
								155
								319
								155
								319
								189
								0
								189
							yourself:
						)
				)
			)
		)
	)
	
	(method (doit &tmp egoLoop egoPriority theX theY)
		
		;EO: added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		
		(= egoLoop (ego loop?))
		(= egoPriority (ego priority?))
		(= theX (ego x?))
		(= theY (ego y?))
		(= local147 0)
		(while (<= local147 24)
			(if (!= [newView local147] 0)
				(localproc_00b0
					[newView (+ local147 1)]
					egoLoop
					[newView (+ local147 2)]
				)
				(if (< [newView local147] -29)
					(= egoKilled 1)
					(= [newView local147] -36)
					(if (== local145 0) (= local145 3))
					(if (== local145 2) (= local145 4))
				)
				([newView (+ local147 3)]
					setCel: local145
					setPri: (- egoPriority 1)
					posn: (+ theX local146) (+ theY [newView local147])
				)
			)
			(= local147 (+ local147 4))
		)
		(= theX (ego x?))
		(cond 
			((and (not local1) (< theX 230))
				(= local1 1)
				(archer1 setScript: shoot1 0 1)
				(archer2 setScript: shoot2 0 2)
				(archer4 setScript: shoot4 0 4)
				(if local149
					(archer3 setScript: shoot3 0 3)
					(archer5 setScript: shoot5 0 5)
				else
					(archer3 stopUpd:)
					(archer5 stopUpd:)
				)
			)
			((and local1 (> theX 270))
				(= local1 0)
				(archer1 setScript: 0)
				(archer2 setScript: 0)
				(archer3 setScript: 0)
				(archer4 setScript: 0)
				(archer5 setScript: 0)
				(curRoom setScript: runAway)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn91)
		(= disabledActions 0)
		(brigandS stop:)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb param2)
		(if (OneOf theVerb V_DETECT V_OPEN V_TRIGGER V_DAZZLE V_ZAP V_CALM V_FLAME V_FETCH)
			(ego setScript: startSpell)
		else
			(switch theVerb
				(V_LOOK (messager say: N_ROOM V_LOOK))
				(else 
					(super doVerb: theVerb param2 &rest)
				)
			)
		)
	)
)

(instance arrowFly of Sound
	(properties
		number 31
	)
)

(instance brigandS of Sound
	(properties
		flags $ffff
		number 73
		priority 3
		loop -1
	)
)

(instance archer1 of Actor
	(properties
		x 39
		y 126
		noun N_ARCHER1
		view 91
		signal $2000
	)
)

(instance archer2 of Actor
	(properties
		x 157
		y 189
		noun N_ARCHER2
		view 91
		loop 1
		signal $2000
	)
)

(instance archer3 of Actor
	(properties
		x 268
		y 62
		noun N_ARCHER3
		view 91
		loop 2
		signal $2000
	)
)

(instance archer4 of Actor
	(properties
		x 18
		y 102
		noun N_ARCHER4
		view 91
		loop 3
		priority 5
		signal $2010
	)
)

(instance archer5 of Actor
	(properties
		x 207
		y 56
		noun N_ARCHER5
		view 91
		loop 4
		signal $2000
	)
)

(instance spearman4 of Actor
	(properties
		x 141
		y 101
		noun N_SPEARMAN4
		view 92
		signal $2000
	)
)

(instance spearman5 of Actor
	(properties
		x 94
		y 119
		noun N_SPEARMAN5
		view 92
		loop 1
		priority 6
		signal $2010
	)
)

(instance stiff1 of View
	(properties
		x 143
		y 87
		noun N_STIFF
		view 90
		loop 6
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO (EgoDead 143 144))
			(V_LOOK (messager say: N_STIFF V_LOOK))
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(instance stiff2 of View
	(properties
		x 86
		y 109
		noun N_STIFF
		view 90
		loop 6
		cel 2
	)
	
	(method (doVerb theVerb)
		(stiff1 doVerb: theVerb)
	)
)

(instance stiff3 of View
	(properties
		x 135
		y 83
		noun N_STIFF
		view 90
		loop 6
		cel 1
	)
	
	(method (doVerb theVerb)
		(stiff1 doVerb: theVerb)
	)
)

(instance theLog of Feature
	(properties
		x 129
		y 110
		noun N_LOG
		nsTop 107
		nsLeft 87
		nsBottom 132
		nsRight 171
		sightAngle 40
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_DO
				(if (< (ego distanceTo: self) 70)
					(ego setScript: climbOverLogs)
				else
					(messager say: N_LOG V_DO)
				)
			)
			(else 
				(super doVerb: theVerb param2 &rest)
			)
		)
	)
)

(class Shooter of Script
	(properties
		arrowPt 0
		arrowID 0
		arrowLoop 0
		addArrowX 0
		addArrowY 0
	)
	
	(method (changeState newState &tmp theX)
		(switch (= state newState)
			(0
				(if (not local1) (self dispose:) (return))
				(= theX (ego x?))
				(= arrowPt (* register 18))
				(switch register
					(1
						(= addArrowX 15)
						(= addArrowY 10)
						(= arrowLoop 1)
					)
					(2
						(= addArrowX 0)
						(= addArrowY 4)
						(cond 
							((< theX 140) (= arrowLoop 2))
							((> theX 225) (= arrowLoop 1))
							(else (= arrowLoop 4))
						)
					)
					(3
						(= addArrowX 3)
						(= addArrowY 1)
						(= arrowLoop 2)
					)
					(4
						(= addArrowX 11)
						(= addArrowY 6)
						(= arrowLoop 1)
					)
					(else 
						(= addArrowX 11)
						(= addArrowY 5)
						(cond 
							((< theX 177) (= arrowLoop 2))
							((> theX 245) (= arrowLoop 1))
							(else (= arrowLoop 4))
						)
					)
				)
				(client cel: 4)
				
				;NRS speed fix
				(= ticks (* 2 (Random 5 15)))
				;(= cycles (Random 5 15))
			)
			(1
				(localproc_0347
					(+ (client x?) addArrowX)
					(+ (client y?) addArrowY)
					arrowPt
				)
				(client setCycle: CycleTo (- (NumCels client) 2) 1 self)
			)
			(2
				(if local149
					((= arrowID (Prop new:))
						view: 90
						setLoop: arrowLoop
						setCel: 0
						setPri: 11
						noun: 6
						ignoreActors:
						posn: [arrowPosn (+ arrowPt 1)] [arrowPosn (+ arrowPt 10)]
						init:
					)
				else
					((= arrowID (Prop new:))
						view: 90
						setLoop: arrowLoop
						setCel: 0
						setPri: 7
						noun: 6
						ignoreActors:
						posn: [arrowPosn (+ arrowPt 1)] [arrowPosn (+ arrowPt 10)]
						init:
					)
				)
				(client setCel: (+ (client cel?) 1))
				;NRS speed fix
				(= ticks 2)
				;(= cycles 1)
			)
			(3
				(arrowFly play:)
				(arrowID
					setCel: 1
					posn: [arrowPosn (+ arrowPt 2)] [arrowPosn (+ arrowPt 11)]
				)
				;NRS speed fix
				(= ticks 2)
				;(= cycles 1)
			)
			(4
				(arrowID
					setCel: 2
					posn: [arrowPosn (+ arrowPt 3)] [arrowPosn (+ arrowPt 12)]
				)
				;NRS speed fix
				(= ticks 2)
				;(= cycles 1)
			)
			(5
				(client setCel: (NumCels client))
				(arrowID
					posn: [arrowPosn (+ arrowPt 4)] [arrowPosn (+ arrowPt 13)]
				)
				;NRS speed fix
				(= ticks 2)
				;(= cycles 1)
			)
			(6
				(arrowID
					setCel: 3
					posn: [arrowPosn (+ arrowPt 5)] [arrowPosn (+ arrowPt 14)]
				)
				;NRS speed fix
				(= ticks 2)
				;(= cycles 1)
			)
			(7
				(if
					(and
						(== (ego script?) 0)
						(localproc_0100
							[arrowPosn (+ arrowPt 5)]
							[arrowPosn (+ arrowPt 14)]
							[arrowPosn (+ arrowPt 6)]
							[arrowPosn (+ arrowPt 15)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						posn: [arrowPosn (+ arrowPt 6)] [arrowPosn (+ arrowPt 15)]
					)
					;NRS speed fix
					(= ticks 2)
					;(= cycles 1)
				)
			)
			(8
				(if
					(and
						(== (ego script?) 0)
						(localproc_0100
							[arrowPosn (+ arrowPt 6)]
							[arrowPosn (+ arrowPt 15)]
							[arrowPosn (+ arrowPt 7)]
							[arrowPosn (+ arrowPt 16)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						setCel: 4
						posn: [arrowPosn (+ arrowPt 7)] [arrowPosn (+ arrowPt 16)]
					)
					;NRS speed fix
					(= ticks 2)
					;(= cycles 1)
				)
			)
			(9
				(if
					(and
						(== (ego script?) 0)
						(localproc_0100
							[arrowPosn (+ arrowPt 7)]
							[arrowPosn (+ arrowPt 16)]
							[arrowPosn (+ arrowPt 8)]
							[arrowPosn (+ arrowPt 17)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						posn: [arrowPosn (+ arrowPt 8)] [arrowPosn (+ arrowPt 17)]
					)
					;NRS speed fix
					(= ticks 2)
					;(= cycles 1)
				)
			)
			(10
				(++ local111)
				(arrowID dispose:)
				(if (or local151 (not local149)) (++ local151))
				(if
				(or (> local151 local112) (> local111 (* 2 local112)))
					(EgoDead 7 8)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance shoot1 of Shooter)

(instance shoot2 of Shooter)

(instance shoot3 of Shooter)

(instance shoot4 of Shooter)

(instance shoot5 of Shooter)

(instance egoHit of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theArrowID dispose:)
				(cond 
					((ego has: iChainmail) (TakeDamage 10))
					((ego has: iLeather) (TakeDamage 20))
					(else (TakeDamage 35))
				)
				(= temp0 0)
				(while (<= temp0 24)
					(if (not [newView temp0])
						(= [newView temp0] theNewView)
						(= [newView (+ temp0 1)] gEgoLoop)
						(= [newView (+ temp0 2)] theNewView_2)
						((= [newView (+ temp0 3)] (View new:))
							view: 90
							setLoop: 5
							setCel: 1
							noun: 6
							ignoreActors:
							init:
						)
						(break)
					)
					(= temp0 (+ temp0 4))
				)
				(if (or (<= [egoStats HEALTH] 0) egoKilled)
					(= ticks 12)
				else
					(self dispose:)
				)
			)
			(1
				(if egoKilled
					(messager say: N_ROOM NULL C_ARROWKILLS 1 self)
				else
					(self cue:)
				)
			)
			(2 (EgoDead 41 42))
		)
	)
)

(instance egoDies of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (EgoDead 41 42))
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff))
			(1
				(if (not (Btst fBeenIn91)) (messager say: N_ROOM 0 C_BADFEELING))
				(= ticks 1)
			)
			(2
				(archer4 setCycle: CycleTo 6 1)
				(archer3 setCycle: CycleTo 7 1 self)
			)
			(3
				(if brigandsBehindLog (spearman4 setCycle: EndLoop))
				(archer1 setCycle: CycleTo 6 1 self)
			)
			(4
				(archer4 stopUpd:)
				(if brigandsBehindLog (spearman5 setLoop: 1 cel: 0 setCycle: EndLoop))
				(archer2 setCycle: CycleTo 5 1 self)
			)
			(5
				(archer5 stopUpd:)
				(archer1 stopUpd:)
				(archer3 stopUpd:)
				(if (not (Btst fBeenIn91)) (messager say: N_ROOM 0 C_WORSEFEELING))
				(archer5 setCycle: CycleTo 8 1 self)
			)
			(6
				(archer2 stopUpd:)
				(if brigandsBehindLog (spearman4 stopUpd:) (spearman5 stopUpd:))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbOverLogs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local149
					(ego setMotion: PolyPath 139 139 self)
				else
					(ego setMotion: PolyPath 141 112 self)
				)
			)
			(1
				(if local149
					(ego setMotion: PolyPath 139 129 self)
				else
					(ego setMotion: PolyPath 141 122 self)
				)
			)
			(2
				(ego view: 400 posn: 141 109 setCel: 0 ignoreActors:)
				(if local149
					(ego setLoop: 0 setPri: 9 setCycle: CycleTo 4 1 self)
				else
					(ego setLoop: 1 setCycle: CycleTo 4 1 self)
				)
			)
			(3
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(if local149
					(ego setPri: 7 setCycle: EndLoop self)
				else
					(ego setPri: 11 setCycle: EndLoop self)
				)
			)
			(4
				(if local149
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									319
									189
									0
									189
									0
									0
									123
									72
									84
									87
									88
									121
									106
									126
									121
									122
									139
									116
									139
									105
									132
									69
									131
									0
									319
									0
								yourself:
							)
					)
					(if brigandsBehindLog
						(curRoom newRoom: vBrigand)
					else
						(= local149 0)
						(ego setPri: 7 posn: 139 122)
					)
				else
					(= local149 1)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									0
									0
									319
									0
									319
									132
									271
									133
									261
									129
									239
									131
									183
									125
									170
									121
									155
									124
									123
									124
									108
									130
									97
									127
									89
									132
									94
									137
									241
									155
									319
									155
									319
									189
									0
									189
								yourself:
							)
					)
					(ego posn: 139 129)
				)
				(HandsOn)
				(NormalEgo)
				(self cue:)
			)
			(5
				(if local149
					(ego setPri: 11)
					(archer3 setScript: shoot3 0 3)
					(archer5 setScript: shoot5 0 5)
				)
				(if (== prevRoomNum vBrigand) (ego ignoreActors:))
				(if (> (++ logJumps) 2) (EgoDead 108 109))
			)
		)
	)
)

(instance runAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 C_ARROWHITS)
				(ego setMotion: PolyPath 335 (ego y?) self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 92)
			)
		)
	)
)

(instance startSpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local1
					(self cue:)
				else
					(= local1 1)
					(archer1 setScript: shoot1 0 1)
					(archer2 setScript: shoot2 0 2)
					(archer4 setScript: shoot4 0 4)
					(if local149
						(archer3 setScript: shoot3 0 3)
						(archer5 setScript: shoot5 0 5)
					else
						(archer3 stopUpd:)
						(archer5 stopUpd:)
					)
					(= ticks 480)
				)
			)
			(1 (EgoDead 155 156))
		)
	)
)
