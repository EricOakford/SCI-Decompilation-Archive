;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm55 0
)

(local
	[upperPts 14] = [319 189 0 189 0 0 314 0 171 51 177 56 319 8]
	[lowerPts 16] = [0 189 0 0 319 0 319 189 43 189 163 146 155 142 26 181]
	[midPts 14] = [319 106 319 189 161 189 161 111 236 145 247 140 176 106]
	[ventPic 5] = [56 55 55 56 56]
	local49
)
(instance rm55 of Room
	(properties
		picture 55
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(self setRegions: DELTAUR)
		(upperPoly points: @upperPts size: 7)
		(lowerPoly points: @lowerPts size: 8)
		(midPoly points: @midPts size: 7)
		(= local49 (if (== prevRoomNum 57) 2 else 1))
		(features add: ladder eachElementDo: #init doit:)
		(ladder approachVerbs: 3)
		(self addObstacle: midPoly)
		(ego
			normal: 0
			init:
			observeControl: cWHITE
			moveSpeed: (theGame egoMoveSpeed?)
			cycleSpeed: (theGame egoMoveSpeed?)
		)
		(if (OneOf local49 1 2)
			(vent init:)
			(if
				(or
					(and (== local49 1) (Btst fOpenedVent1))
					(and (== local49 2) (Btst fOpenedVent2))
				)
				(vent cel: 4 ignoreActors: TRUE stopUpd:)
			else
				(vent cel: 0 ignoreActors: FALSE stopUpd:)
			)
		else
			(vent dispose:)
		)
		(super init:)
		(self setScript: fromGrate)
		(if (!= (theMusic number?) 509)
			(theMusic number: 509 loop: -1 play:)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script 0)
			((ego isBlocked:) (Print 55 2) (ego setMotion: 0))
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(if (OneOf local49 1 2) (Print 55 0) else (Print 55 1))
		else
			(super doVerb: &rest)
		)
	)
)

(instance fromGrate of Script
	(properties)
	
	(method (doit &tmp theControl)
		(asm
			pushi    #canControl
			pushi    0
			class    User
			send     4
			not     
			bnt      code_0227
			ldi      0
			jmp      code_030f
code_0227:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			sat      theControl
			push    
			ldi      8
			and     
			bnt      code_025b
			pushi    #cel
			pushi    0
			lofsa    vent
			send     4
			bnt      code_025b
			pTos     state
			ldi      2
			eq?     
			bnt      code_025b
			pushi    #setScript
			pushi    1
			lofsa    toGrate2
			push    
			pToa     client
			send     6
			jmp      code_030f
code_025b:
			lst      theControl
			ldi      16
			and     
			bnt      code_0272
			pushi    #setScript
			pushi    1
			lofsa    ontoLadderFromMid
			push    
			pToa     client
			send     6
			jmp      code_030f
code_0272:
			pushi    #isStopped
			pushi    0
			lag      ego
			send     4
			bnt      code_0289
			pushi    #setCycle
			pushi    1
			pushi    0
			lag      ego
			send     6
			jmp      code_030f
code_0289:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_02cd
			pprev   
			ldi      130
			lt?     
			bnt      code_02cd
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_02bb
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      65535
			eq?     
code_02bb:
			not     
			bnt      code_02cd
			pushi    #setCycle
			pushi    1
			class    Reverse
			push    
			lag      ego
			send     6
			jmp      code_030f
code_02cd:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_02df
			pprev   
			ldi      130
			lt?     
code_02df:
			not     
			bnt      code_030f
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0300
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      1
			eq?     
code_0300:
			not     
			bnt      code_030f
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lag      ego
			send     6
code_030f:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(vent startUpd: ignoreActors: 1)
				(ego view: 38 setLoop: 3 posn: 257 148 setStep: 2 2)
				(if (> (theGame detailLevel:) 1)
					(self setScript: scurryRats1)
				else
					(= ticks 18)
				)
			)
			(1
				(ego setCycle: Forward setMotion: PolyPath 223 133 self)
			)
			(2
				(vent ignoreActors: FALSE stopUpd:)
				(HandsOn)
			)
		)
	)
)

(instance scurryRats1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat1
					init:
					cel: 0
					setLoop: 14
					cycleSpeed: 3
					x: 202
					y: 38
					setCycle: EndLoop
				)
				(rat2
					init:
					cel: 0
					x: 116
					y: 162
					setLoop: 14
					cycleSpeed: 3
					setCycle: EndLoop
				)
				(rat3
					init:
					cel: 0
					setLoop: 15
					cycleSpeed: 3
					x: 105
					y: 161
					setCycle: EndLoop
				)
				(= seconds 3)
			)
			(1
				(rat1 setCycle: BegLoop)
				(rat2 setCycle: BegLoop)
				(rat3 setCycle: BegLoop self)
			)
			(2
				(rat1 setCycle: Walk setLoop: 12 setMotion: MoveTo 240 27)
				(rat2
					setCycle: Walk
					setLoop: 12
					setMotion: MoveTo 155 151
				)
				(rat3
					setCycle: Walk
					setLoop: 13
					setMotion: MoveTo 51 183 self
				)
			)
			(3
				(rat1 dispose:)
				(rat3 dispose:)
				(rat2 setLoop: 13 setMotion: MoveTo 51 183 self)
			)
			(4
				(rat2 dispose:)
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scurryRats2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat2
					init:
					cel: 0
					setLoop: 14
					x: 116
					y: 162
					cycleSpeed: 3
					setCycle: EndLoop
				)
				(rat3
					init:
					cel: 0
					setLoop: 15
					x: 105
					y: 161
					cycleSpeed: 3
					setCycle: EndLoop
				)
				(= seconds 2)
			)
			(1
				(rat2 setCycle: BegLoop)
				(rat3 setCycle: BegLoop self)
			)
			(2
				(rat2
					setCycle: Walk
					setLoop: 12
					setMotion: MoveTo 155 151
				)
				(rat3
					setCycle: Walk
					setLoop: 13
					setMotion: MoveTo 51 183 self
				)
			)
			(3
				(rat3 dispose:)
				(rat2 setLoop: 13 setMotion: MoveTo 51 183 self)
			)
			(4
				(rat2 dispose:)
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance scurryRats3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat1
					init:
					cel: 0
					setLoop: 14
					cycleSpeed: 3
					x: 202
					y: 38
					setCycle: EndLoop
				)
				(= seconds 2)
			)
			(1 (rat1 setCycle: BegLoop self))
			(2
				(rat1
					setCycle: Walk
					setLoop: 12
					setMotion: MoveTo 240 27 self
				)
			)
			(3
				(rat1 dispose:)
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance randomRatLow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat2 init: hide:)
				(= seconds (Random 10 20))
			)
			(1
				(rat2
					show:
					cel: 0
					setLoop: 12
					cycleSpeed: 3
					x: 47
					y: 185
					setCycle: Walk
					setMotion: MoveTo 131 155 self
				)
			)
			(2
				(rat2 setLoop: 14 cel: 0 setCycle: EndLoop)
				(= seconds 2)
			)
			(3 (rat2 setCycle: BegLoop self))
			(4
				(rat2
					setCycle: Walk
					setLoop: 13
					setMotion: MoveTo 47 185 self
				)
			)
			(5
				(rat2 dispose:)
				(self changeState: 0)
			)
		)
	)
)

(instance randomRatHi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rat1 init: hide:)
				(= seconds (Random 10 20))
			)
			(1
				(rat1
					show:
					cel: 0
					setLoop: 13
					cycleSpeed: 3
					x: 236
					y: 30
					setCycle: Walk
					setMotion: MoveTo 180 44 self
				)
			)
			(2
				(rat1 setLoop: 15 cel: 0 setCycle: EndLoop)
				(= seconds 2)
			)
			(3 (rat1 setCycle: BegLoop self))
			(4
				(rat1
					setLoop: 12
					setCycle: Walk
					setMotion: MoveTo 236 30 self
				)
			)
			(5
				(rat1 dispose:)
				(self changeState: 0)
			)
		)
	)
)

(instance toGrate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LoadMany SOUND 529 509)
				(Print 55 3)
				(SolvePuzzle 3 fExitVentilationShaft)
				(ego setLoop: 11 posn: 244 149 cel: 0 setCycle: EndLoop self)
			)
			(1
				(soundFx number: 529 loop: 1 play:)
				(ego setPri: 14)
				(vent startUpd: setCycle: EndLoop self)
			)
			(2
				(if (== local49 1)
					(Bset fOpenedVent1)
					(curRoom newRoom: 54)
				else
					(Bset fOpenedVent2)
					(curRoom newRoom: 57)
				)
			)
		)
	)
)

(instance toGrate2 of Script
	(properties)
	
	(method (doit &tmp theControl)
		(asm
			pushi    #canControl
			pushi    0
			class    User
			send     4
			not     
			bnt      code_0b21
			ldi      0
			jmp      code_0bbe
code_0b21:
			pushi    #isStopped
			pushi    0
			lag      ego
			send     4
			bnt      code_0b38
			pushi    #setCycle
			pushi    1
			pushi    0
			lag      ego
			send     6
			jmp      code_0bbe
code_0b38:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0b7c
			pprev   
			ldi      130
			lt?     
			bnt      code_0b7c
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0b6a
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      65535
			eq?     
code_0b6a:
			not     
			bnt      code_0b7c
			pushi    #setCycle
			pushi    1
			class    Reverse
			push    
			lag      ego
			send     6
			jmp      code_0bbe
code_0b7c:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0b8e
			pprev   
			ldi      130
			lt?     
code_0b8e:
			not     
			bnt      code_0bbe
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0baf
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      1
			eq?     
code_0baf:
			not     
			bnt      code_0bbe
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lag      ego
			send     6
code_0bbe:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(vent startUpd: ignoreActors: TRUE)
				(ego setPri: 14 setMotion: MoveTo 255 151 self)
			)
			(1
				(if (== local49 1)
					(curRoom newRoom: 54)
				else
					(curRoom newRoom: 57)
				)
			)
		)
	)
)

(instance toLowVent of Script
	(properties)
	
	(method (doit &tmp theControl)
		(asm
			pushi    #canControl
			pushi    0
			class    User
			send     4
			not     
			bnt      code_0c77
			ldi      0
			jmp      code_0d16
code_0c77:
			pushi    #isStopped
			pushi    0
			lag      ego
			send     4
			bnt      code_0c8e
			pushi    #setCycle
			pushi    1
			pushi    0
			lag      ego
			send     6
			jmp      code_0d16
code_0c8e:
			pushi    190
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0cd3
			pprev   
			ldi      290
			lt?     
			bnt      code_0cd3
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0cc1
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      65535
			eq?     
code_0cc1:
			not     
			bnt      code_0cd3
			pushi    #setCycle
			pushi    1
			class    Reverse
			push    
			lag      ego
			send     6
			jmp      code_0d16
code_0cd3:
			pushi    190
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0ce6
			pprev   
			ldi      290
			lt?     
code_0ce6:
			not     
			bnt      code_0d16
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0d07
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      1
			eq?     
code_0d07:
			not     
			bnt      code_0d16
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lag      ego
			send     6
code_0d16:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local49 (mod (++ local49) 4))
				(HandsOff)
				(curRoom drawPic: [ventPic local49])
				(if (OneOf local49 1 2)
					(vent init:)
					(if
						(or
							(and (== local49 1) (Btst fOpenedVent1))
							(and (== local49 2) (Btst fOpenedVent2))
						)
						(vent cel: 4 ignoreActors: 1 stopUpd:)
					else
						(vent cel: 0 ignoreActors: 0 stopUpd:)
					)
				else
					(vent dispose:)
				)
				((curRoom obstacles?) delete: upperPoly add: lowerPoly)
				(ego setCycle: 0 posn: 64 180)
				(if (> (theGame detailLevel:) 1)
					(self setScript: scurryRats3)
				else
					(= ticks 18)
				)
			)
			(1
				(ego
					setCycle: (if (== (ego loop?) 1) Reverse else Forward)
					setMotion: MoveTo 104 167 self
				)
			)
			(2
				(crawlLow start: 3)
				(client setScript: crawlLow)
			)
		)
	)
)

(instance toHiVent of Script
	(properties)
	
	(method (doit &tmp theControl)
		(asm
			pushi    #canControl
			pushi    0
			class    User
			send     4
			not     
			bnt      code_0ea1
			ldi      0
			jmp      code_0f3e
code_0ea1:
			pushi    #isStopped
			pushi    0
			lag      ego
			send     4
			bnt      code_0eb8
			pushi    #setCycle
			pushi    1
			pushi    0
			lag      ego
			send     6
			jmp      code_0f3e
code_0eb8:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0efc
			pprev   
			ldi      130
			lt?     
			bnt      code_0efc
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0eea
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      65535
			eq?     
code_0eea:
			not     
			bnt      code_0efc
			pushi    #setCycle
			pushi    1
			class    Reverse
			push    
			lag      ego
			send     6
			jmp      code_0f3e
code_0efc:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0f0e
			pprev   
			ldi      130
			lt?     
code_0f0e:
			not     
			bnt      code_0f3e
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_0f2f
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      1
			eq?     
code_0f2f:
			not     
			bnt      code_0f3e
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lag      ego
			send     6
code_0f3e:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local49 (-- local49) else (= local49 4))
				(curRoom drawPic: [ventPic local49])
				(if (OneOf local49 1 2)
					(vent init:)
					(if
						(or
							(and (== local49 1) (Btst fOpenedVent1))
							(and (== local49 2) (Btst fOpenedVent2))
						)
						(vent cel: 4 ignoreActors: TRUE stopUpd:)
					else
						(vent cel: 0 ignoreActors: FALSE stopUpd:)
					)
				else
					(vent dispose:)
				)
				((curRoom obstacles?) delete: lowerPoly add: upperPoly)
				(ego setCycle: 0 posn: 238 34)
				(if (> (theGame detailLevel:) 1)
					(self setScript: scurryRats2)
				else
					(= ticks 18)
				)
			)
			(1
				(if (== (ego loop?) 2)
					(ego setCycle: Reverse)
				else
					(ego setCycle: Forward)
				)
				(ego setMotion: MoveTo 212 39 self)
			)
			(2
				(crawlHigh start: 4)
				(client setScript: crawlHigh)
			)
		)
	)
)

(instance onLadder of Script
	(properties)
	
	(method (doit)
		(cond 
			((not (User canControl:)) 0)
			(
				(and
					(== (curRoom curPic?) 55)
					(< 60 (ego heading?))
					(< (ego heading?) 120)
				)
				(client setScript: crawlMid)
			)
			((> (ego y?) 121) (client setScript: crawlLow))
			((< (ego y?) 121) (client setScript: crawlHigh))
		)
		(super doit:)
	)
)

(instance ontoLadderFromMid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) delete: midPoly)
				(ego setStep: 1 4)
				(if (ego loop?)
					(ego setCycle: Forward setMotion: MoveTo 172 109 self)
				else
					(ego setCycle: Reverse setMotion: MoveTo 160 106 self)
				)
			)
			(1
				(if (ego loop?)
					(ego setLoop: 10 cel: 0 setCycle: EndLoop self)
				else
					(ego setLoop: 9 cel: 6 setCycle: BegLoop self)
				)
			)
			(2
				(ego setLoop: 4 cel: 0 posn: 158 121)
				(HandsOn)
				(client setScript: onLadder)
			)
		)
	)
)

(instance ontoLadderFromBottom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) delete: lowerPoly)
				(if (== (ego loop?) 2)
					(ego setMotion: MoveTo 138 149 self)
				else
					(ego setMotion: MoveTo 155 149 self)
				)
			)
			(1
				(ego
					posn: 155 147
					setLoop: 5
					cel: (if (== (ego loop?) 2) 4 else (ego lastCel:))
					setCycle: BegLoop self
				)
			)
			(2
				(ego posn: 159 141 setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 4 cel: 0 posn: 158 121)
				(HandsOn)
				(client setScript: onLadder)
			)
		)
	)
)

(instance ontoLadderFromTopBackwards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) delete: upperPoly)
				(ego setLoop: 2 setMotion: MoveTo 164 52 self)
			)
			(1
				(ego
					setLoop: 7
					cel: (ego lastCel:)
					posn: 164 52
					setCycle: BegLoop self
				)
			)
			(2
				(ego setLoop: 4 cel: 1 posn: 160 104 setCycle: BegLoop self)
			)
			(3
				(ego
					posn: 158 121
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(4
				(HandsOn)
				(client setScript: onLadder)
			)
		)
	)
)

(instance ontoLadderFromTopForwards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) delete: upperPoly)
				(ego setCycle: Forward setMotion: MoveTo 164 52 self)
			)
			(1
				(ego setLoop: 5 cel: 8 posn: 164 52 setCycle: CycleTo 4 1 self)
			)
			(2
				(ego setLoop: 7 cel: 7 posn: 168 57 setCycle: BegLoop self)
			)
			(3
				(ego setLoop: 4 cel: 1 posn: 160 104 setCycle: BegLoop self)
			)
			(4
				(ego
					posn: 158 121
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(5
				(HandsOn)
				(client setScript: onLadder)
			)
		)
	)
)

(instance crawlHigh of Script
	(properties)
	
	(method (doit &tmp theControl)
		(cond 
			((not (User canControl:)) 0)
			((& (= theControl (ego onControl: origin)) cLBLUE)
				(rat2 setScript: 0)
				(rat2 dispose: 0)
				(client setScript: toLowVent)
			)
			((& theControl cLGREEN)
				(rat2 setScript: 0)
				(rat2 dispose: 0)
				(client
					setScript:
						(if (ego loop?)
							ontoLadderFromTopBackwards
						else
							ontoLadderFromTopForwards
						)
				)
			)
			((ego isStopped:) (ego setCycle: 0))
			(
			(and (< 160 (ego heading?)) (< (ego heading?) 340))
				(cond 
					((== (ego loop?) 1)
						(if
							(not
								(if (ego cycler?) (== ((ego cycler?) cycleDir?) 1))
							)
							(ego setCycle: Forward)
						)
					)
					(
						(not
							(if (ego cycler?) (== ((ego cycler?) cycleDir?) -1))
						)
						(ego setCycle: Reverse)
					)
				)
			)
			(
				(not
					(if (< 160 (ego heading?)) (< (ego heading?) 340))
				)
				(cond 
					((== (ego loop?) 1)
						(if
							(not
								(if (ego cycler?) (== ((ego cycler?) cycleDir?) -1))
							)
							(ego setCycle: Reverse)
						)
					)
					(
						(not
							(if (ego cycler?) (== ((ego cycler?) cycleDir?) 1))
						)
						(ego setCycle: Forward)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) add: upperPoly)
				(ego setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(1
				(ego cel: 0 posn: 160 104 setCycle: CycleTo 1 1 self)
			)
			(2
				(ego setLoop: 7 cel: 0 posn: 170 56 setCycle: EndLoop self)
			)
			(3
				(ego
					setLoop: 2
					setStep: 2 2
					setCycle: Forward
					setMotion: MoveTo 190 46 self
				)
			)
			(4
				(if (> (theGame detailLevel:) 1)
					(rat2 setScript: randomRatLow)
				)
				(= start 0)
				(HandsOn)
			)
		)
	)
)

(instance crawlLow of Script
	(properties)
	
	(method (doit &tmp theControl)
		(cond 
			((not (User canControl:)) 0)
			((& (= theControl (ego onControl: origin)) cBLUE)
				(rat1 setScript: 0)
				(rat1 dispose: 0)
				(client setScript: toHiVent)
			)
			((& theControl cGREEN)
				(rat1 setScript: 0)
				(rat1 dispose: 0)
				(client setScript: ontoLadderFromBottom)
			)
			((ego isStopped:) (ego setCycle: 0))
			(
			(and (< 160 (ego heading?)) (< (ego heading?) 340))
				(cond 
					((== (ego loop?) 1)
						(if
							(not
								(if (ego cycler?) (== ((ego cycler?) cycleDir?) 1))
							)
							(ego setCycle: Forward)
						)
					)
					(
						(not
							(if (ego cycler?) (== ((ego cycler?) cycleDir?) -1))
						)
						(ego setCycle: Reverse)
					)
				)
			)
			(
				(not
					(if (< 160 (ego heading?)) (< (ego heading?) 340))
				)
				(cond 
					((== (ego loop?) 1)
						(if
							(not
								(if (ego cycler?) (== ((ego cycler?) cycleDir?) -1))
							)
							(ego setCycle: Reverse)
						)
					)
					(
						(not
							(if (ego cycler?) (== ((ego cycler?) cycleDir?) 1))
						)
						(ego setCycle: Forward)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) add: lowerPoly)
				(ego posn: 159 141 setLoop: 4 cel: 4 setCycle: BegLoop self)
			)
			(1
				(ego posn: 155 147 setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego
					setLoop: 1
					cel: 6
					posn: 155 149
					setCycle: Forward
					setStep: 2 2
					setMotion: MoveTo 128 158 self
				)
			)
			(3
				(if (> (theGame detailLevel:) 1)
					(rat1 setScript: randomRatHi)
				)
				(= start 0)
				(HandsOn)
			)
		)
	)
)

(instance crawlMid of Script
	(properties)
	
	(method (doit &tmp theControl)
		(asm
			pushi    #canControl
			pushi    0
			class    User
			send     4
			not     
			bnt      code_1ba5
			ldi      0
			jmp      code_1cd1
code_1ba5:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			sat      theControl
			push    
			ldi      8
			and     
			bnt      code_1bf7
			pushi    #cel
			pushi    0
			lofsa    vent
			send     4
			bnt      code_1bf7
			pushi    #setScript
			pushi    1
			pushi    0
			lofsa    rat1
			send     6
			pushi    #dispose
			pushi    1
			pushi    0
			lofsa    rat1
			send     6
			pushi    #setScript
			pushi    1
			pushi    0
			lofsa    rat2
			send     6
			pushi    #dispose
			pushi    1
			pushi    0
			lofsa    rat2
			send     6
			pushi    #setScript
			pushi    1
			lofsa    toGrate2
			push    
			pToa     client
			send     6
			jmp      code_1cd1
code_1bf7:
			lst      theControl
			ldi      16
			and     
			bnt      code_1c34
			pushi    #setScript
			pushi    1
			pushi    0
			lofsa    rat1
			send     6
			pushi    #setScript
			pushi    1
			pushi    0
			lofsa    rat2
			send     6
			pushi    #dispose
			pushi    1
			pushi    0
			lofsa    rat1
			send     6
			pushi    #dispose
			pushi    1
			pushi    0
			lofsa    rat2
			send     6
			pushi    #setScript
			pushi    1
			lofsa    ontoLadderFromMid
			push    
			pToa     client
			send     6
			jmp      code_1cd1
code_1c34:
			pushi    #isStopped
			pushi    0
			lag      ego
			send     4
			bnt      code_1c4b
			pushi    #setCycle
			pushi    1
			pushi    0
			lag      ego
			send     6
			jmp      code_1cd1
code_1c4b:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_1c8f
			pprev   
			ldi      130
			lt?     
			bnt      code_1c8f
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_1c7d
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      1
			eq?     
code_1c7d:
			not     
			bnt      code_1c8f
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lag      ego
			send     6
			jmp      code_1cd1
code_1c8f:
			pushi    30
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_1ca1
			pprev   
			ldi      130
			lt?     
code_1ca1:
			not     
			bnt      code_1cd1
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			bnt      code_1cc2
			pushi    #cycleDir
			pushi    0
			pushi    #cycler
			pushi    0
			lag      ego
			send     4
			send     4
			push    
			ldi      65535
			eq?     
code_1cc2:
			not     
			bnt      code_1cd1
			pushi    #setCycle
			pushi    1
			class    Reverse
			push    
			lag      ego
			send     6
code_1cd1:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((curRoom obstacles?) add: midPoly)
				(ego posn: 161 108 setLoop: 9 cel: 0 setCycle: EndLoop self)
			)
			(1
				(ego
					setLoop: 0
					cel: 0
					posn: 162 108
					setCycle: Forward
					setStep: 2 2
					setMotion: MoveTo 183 117 self
				)
			)
			(2
				(if (> (theGame detailLevel:) 1)
					(rat2 setScript: randomRatLow)
					(rat1 setScript: randomRatHi)
				)
				(HandsOn)
			)
		)
	)
)

(instance upperPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance lowerPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance midPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance vent of Prop
	(properties
		x 278
		y 144
		description {vent grill}
		view 154
		loop 11
		priority 13
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (and (== cel 0) (& (ego onControl: origin) cCYAN))
					(curRoom setScript: toGrate)
				else
					(Print 55 4)
				)
			)
			(verbLook
				(if (not cel) (Print 55 5) else (Print 55 6))
			)
			(verbTaste
				(Print 55 7)
			)
			(verbSmell
				(Print 55 8)
			)
			(verbUse
				(Print 55 9)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rat1 of Actor
	(properties
		x 202
		y 38
		description {rat}
		lookStr {No ship would be complete without official ship rats.}
		view 38
		loop 2
		signal ignrAct
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 55 10)
			)
			(verbLook
				(Print 55 10)
			)
			(verbTaste
				(Print 55 10)
			)
			(verbSmell
				(Print 55 10)
			)
			(verbUse
				(Print 55 10)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rat2 of Actor
	(properties
		x 116
		y 162
		description {rat}
		lookStr {No ship would be complete without official ship rats.}
		view 38
		loop 2
		signal ignrAct
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(rat1 doVerb: theVerb &rest)
	)
)

(instance rat3 of Actor
	(properties
		x 105
		y 161
		description {rat}
		lookStr {No ship would be complete without official ship rats.}
		view 38
		loop 3
		signal ignrAct
		cycleSpeed 3
	)
	
	(method (doVerb theVerb)
		(rat1 doVerb: theVerb &rest)
	)
)

(instance ladder of Feature
	(properties
		x 72
		y 42
		description {ladder}
		sightAngle 0
		onMeCheck $4000
		approachX 160
		approachY 90
		lookStr {The ladder seems to provide access to vent shafts above and below this one.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 55 11)
			)
			(verbLook
				(Print 55 12)
			)
			(verbSmell
				(Print 55 13)
			)
			(verbTaste
				(Print 55 14)
			)
			(verbUse
				(Print 55 15)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
