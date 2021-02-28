;;; Sierra Script 1.0 - (do not remove this comment)
(script# 471)
(include sci.sh)
(use Main)
(use GloryControls)
(use rm470)
(use IconBar)
(use PolyPath)
(use StopWalk)
(use Timer)
(use Window)
(use Motion)
(use User)
(use System)

(public
	fallDown 2
	uhuraCompete 3
	yesufuCompete 4
	oppActs 5
	proc471_6 6
	proc471_7 7
	proc471_8 8
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	[local8 10] = [0 0 2 3 1 0 0 2 2 4]
	local18
	local19
	local20
	local21
	local22
	local23
	local24
	local25
	local26
	local27
	local28
	local29
	local30
	local31
	local32
	local33
	[local34 20] = [0 0 0 0 0 0 4 1 14 1 7 8 0 11 0 19 5 25]
)
(procedure (proc471_6 param1 param2)
	(= local2 param1)
	(= local3 param2)
	(= local4
		(= local5
			(= local6
				(= local7
					(= local18
						(= local19
							(= local20
								(= local21
									(= local24
										(= local25
											(= local26 (= local27 (= local29 (= local30 0))))
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
	(if (== param1 1)
		(= local23 (+ 150 (* local3 25)))
		(= local31 983)
		(= local32 472)
		(= local33 473)
		(= local1 (ScriptID 39 1))
	else
		(= local23 (+ 170 (* local3 25)))
		(= local31 971)
		(= local32 471)
		(= local33 474)
		(= local1 (ScriptID 34 1))
	)
)

(procedure (proc471_7 &tmp temp0)
	(if (!= local3 1)
		(if (not (= temp0 local0)) (bridgeControls init:))
		(SetPort (bridgeWin window?))
		(bridgeControls enable: 0 1 2 3 eachElementDo: #show)
		(theGame setCursor: 999)
		(if (not temp0) (bridgeControls show: dispose:))
	)
)

(procedure (proc471_8 &tmp temp0)
	(if (not (= temp0 local0)) (thiefControls init:))
	(SetPort (bridgeWin window?))
	(thiefControls enable: 4 0 1 3 2 5 6 eachElementDo: #show)
	(theGame setCursor: 999)
	(if (not temp0) (thiefControls show: dispose:))
)

(procedure (localproc_012e param1 &tmp temp0)
	(= temp0 local28)
	(= local28 0)
	(if (and (IsObject temp0) (temp0 isKindOf: Timer))
		(if param1 (temp0 seconds: -1 ticks: 1 doit:))
		(temp0 dispose: delete:)
	)
)

(procedure (localproc_01e4 &tmp theThiefControls)
	(cond 
		((== origHeroType 2) (= theThiefControls thiefControls))
		((!= local3 1) (= theThiefControls bridgeControls))
		(else (return))
	)
	(theThiefControls
		state: (& (bridgeControls state?) $ffdf)
	)
)

(procedure (localproc_0219)
	(if (and (!= local3 1) local0)
		(SetPort (bridgeWin window?))
		(bridgeControls disable: 0 1 2 3 eachElementDo: #show)
		(theGame setCursor: 999)
		(SetPort 0)
	)
)

(procedure (localproc_02ae)
	(if local0
		(SetPort (bridgeWin window?))
		(thiefControls disable: 4 0 1 3 2 5 eachElementDo: #show)
		(theGame setCursor: 999)
	)
)

(procedure (localproc_033a &tmp temp0 temp1 temp2)
	(= temp0 (- [egoStats 2] local23))
	(if
		(<
			(= temp1 (+ (if local21 50 else 25) (/ temp0 5)))
			15
		)
		(= temp1 15)
	)
	(if (<= (Random 1 100) temp1)
		(= local25 1)
		(while (== local19 (= temp2 (Random 1 4)))
		)
		(= local24 temp2)
	else
		(= local25 0)
		(= local24 local19)
	)
)

(procedure (localproc_0392 param1 &tmp temp0)
	(localproc_012e 0)
	(switch local3
		(1
			(if (< [egoStats 2] (- local23 (Random 1 40)))
				(while (== local24 (= temp0 (Random 1 4)))
				)
				(= local19 temp0)
				(if local20
					(= local18 1)
				else
					(= local20 1)
					(= local25 -1)
				)
				(= local27 -1)
			else
				(= local25 1)
				(= local27 0)
				(if (== (= local19 local24) 0)
					(= local19 (Random 1 4))
				)
			)
			(ego setScript: egoActs param1 local19)
		)
		(2
			(= local28 (Timer setReal: param1 5))
		)
		(3
			(= local28 (Timer setReal: param1 3))
		)
	)
)

(procedure (localproc_043a param1)
	(if
		(or
			(<= (ego trySkill: 2 (+ 140 (* local3 25))) 0)
			(< [egoStats 17] 20)
		)
		(ego useSkill: 2 100)
		(proc470_1)
		(param1 setScript: fallDown param1)
	else
		(ego view: 12 setLoop: 0 setCycle: Walk)
		(if (!= origHeroType 2)
			(if (!= local3 1)
				(if (uhuraCompete client?)
					(localproc_012e 1)
				else
					(proc471_7)
				)
			)
		else
			(ego view: 12 loop: 0 cel: 0)
			(proc471_8)
		)
		(param1 dispose:)
	)
)

(procedure (localproc_04d7 param1 param2)
	(param2 setScript: oppActs param2 param1)
)

(procedure (localproc_04e9)
	(local1
		view: local32
		loop: 1
		cel: (if (== local1 (ScriptID 34 1)) 5 else 0)
		forceUpd:
	)
)

(instance yesufuCompete of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 12
					setScale:
					setLoop: 0
					scaleX: 109
					scaleY: 109
					setPri: 10
					x: 135
					y: 80
					ignoreActors: 1
					init:
				)
				((ScriptID 39 1)
					view: 473
					loop: 1
					cel: 0
					setScale:
					scaleX: 109
					scaleY: 109
					setPri: 10
					posn: 160 76
					ignoreActors: 1
					init:
				)
				(= cycles 1)
			)
			(1
				(client setScript: yesufuTimer)
			)
		)
	)
)

(instance yesufuTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= cycles 1))
			(1
				1
				(if (> [egoStats 2] local23)
					(self setScript: egosTurn self)
				else
					(self setScript: oppsTurn self)
				)
			)
			(2 (= cycles 2))
			(3
				3
				(if (== local6 0)
					(self setScript: egosTurn self)
				else
					(self setScript: oppsTurn self)
				)
			)
			(4 4 (self changeState: 2))
		)
	)
)

(instance fallDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_012e 0)
				(++ local30)
				(= local18 1)
				(HandsOff)
				(localproc_01e4)
				(= cycles 10)
			)
			(1
				(ego
					view: 6
					cel: 0
					loop: 4
					cycleSpeed: 10
					setCycle: CT 2 1
					setPri: 11
					moveSpeed: 1
					origStep: 522
					setMotion: MoveTo (+ (ego x?) 10) 143 self
				)
			)
			(2
				(globalSound number: 920 setLoop: 1 play: 127)
				(ego setCycle: End self)
			)
			(3
				(ego cel: 0 loop: 6 setCycle: End self)
			)
			(4
				(cond 
					((< [egoStats 17] 20) (messager say: 4 6 38 0 self 470))
					(
						(or
							(not (uhuraCompete client?))
							(<= (uhuraCompete state?) 18)
							(== local30 3)
						)
						(= cycles 1)
					)
					((Random 0 1) (messager say: 2 6 37 0 self 470))
					(else (messager say: 2 6 35 0 self 470))
				)
			)
			(5
				(if (not (ego takeDamage: 5)) (= [egoStats 16] 1))
				(ego
					view: 0
					cycleSpeed: 6
					ignoreActors: 0
					moveSpeed: 6
					normalize:
					setMotion: PolyPath 32 143 self
				)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance uhuraCompete of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				((ScriptID 34 1) setMotion: PolyPath 287 144 self)
			)
			(1
				1
				((ScriptID 34 1)
					view: local32
					setStep: 3 3
					setLoop: 0
					cel: 0
					setPri: 10
					setCycle: Fwd
					setMotion: MoveTo 287 132 self
				)
			)
			(2
				2
				((ScriptID 34 1)
					loop: 2
					cel: 0
					setScale:
					scaleX: 109
					scaleY: 109
					x: 287
					y: 105
					setCycle: End self
				)
			)
			(3
				((ScriptID 34 1)
					loop: (== 1 3)
					cel: 0
					x: 292
					y: 76
					setCycle: End self
				)
			)
			(4
				4
				((ScriptID 34 1)
					loop: 1
					setStep: 3 2
					setCycle: Walk
					scaleX: 109
					scaleY: 109
					setMotion: MoveTo 160 ((ScriptID 34 1) y?) self
				)
			)
			(5 5 (= ticks 1))
			(6 6 (= cycles 12))
			(7
				7
				(if (== (curRoom notify: 3) 4)
					(ego setMotion: MoveTo 135 (ego y?) self)
				else
					(messager say: 2 6 23 0 self 470)
				)
			)
			(8
				8
				(if (== (curRoom notify: 3) 4)
					(self changeState: 19)
				else
					(ego
						solvePuzzle: 279 3 9
						setMotion: MoveTo 135 (ego y?) self
					)
				)
			)
			(9
				9
				(= local4 1)
				(messager say: 2 6 24 0 self 470)
			)
			(10
				10
				(switch local4
					(1
						(messager say: 2 6 25 0 self 470)
					)
					(3
						(messager say: 2 6 29 0 self 470)
					)
					(2
						(messager say: 2 6 27 0 self 470)
					)
					(4
						(messager say: 2 6 31 0 self 470)
					)
				)
			)
			(11 11 (= ticks 30))
			(12
				12
				(if (== register 1)
					(globalSound number: 922 setLoop: 1 play: 127)
				)
				(local1
					view: local33
					loop: [local8 local4]
					cel: 0
					setCycle: End self
				)
			)
			(13
				13
				(if (== local3 1)
					(= ticks (Random 10 30))
				else
					(= ticks 1)
				)
			)
			(14
				14
				(= local19 0)
				(localproc_012e 0)
				(switch local3
					(1
						(= local19 local4)
						(ego setScript: egoActs self local19)
						(= cycles (Random 10 30))
					)
					(2
						(= local28 (Timer setReal: self 5))
					)
					(3
						(= local28 (Timer setReal: self 3))
					)
				)
				(proc471_7)
			)
			(15
				15
				(localproc_012e 0)
				(if (ego script?)
					(= state (- state 1))
					(= ticks 1)
				else
					(= ticks 1)
				)
			)
			(16
				16
				(localproc_0219)
				(= local30 0)
				(cond 
					(local18
						(switch local4
							(1
								(messager say: 2 6 26 0 0 470)
							)
							(3
								(messager say: 2 6 28 0 0 470)
							)
							(2
								(messager say: 2 6 30 0 0 470)
							)
							(4
								(messager say: 2 6 32 0 0 470)
							)
						)
						(ego setScript: egoFallGetUp self)
					)
					((== local19 local4)
						(if (== local4 4)
							(self changeState: 18)
						else
							(++ local4)
							(self changeState: 10)
						)
					)
					(else (ego setScript: egoFallGetUp self))
				)
			)
			(17
				17
				(localproc_012e 0)
				(self changeState: 10)
			)
			(18
				18
				(messager say: 2 6 2 1 self 470)
			)
			(19
				19
				(messager say: 2 6 2 2 self 470)
			)
			(20
				20
				(if (> [egoStats 2] local23)
					(self setScript: egosTurn self)
				else
					(self setScript: oppsTurn self)
				)
			)
			(21 21 (= cycles 2))
			(22
				22
				(if (== local6 0)
					(self setScript: egosTurn self)
				else
					(self setScript: oppsTurn self)
				)
			)
			(23 23 (self changeState: 21))
		)
	)
)

(instance egosTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local3 1)
					(= ticks (Random 10 30))
				else
					(= ticks 1)
				)
			)
			(1
				(= local19 0)
				(localproc_0392 self)
				(proc471_7)
			)
			(2
				(localproc_012e 0)
				(if (ego script?) (= state (- state 1)))
				(= ticks 1)
			)
			(3
				(localproc_0219)
				(= ticks 1)
			)
			(4
				(cond 
					(local18
						(= local25 -1)
						(= local18 0)
						(ego setScript: egoFallGetUp self)
					)
					((== local19 0) (= local6 1) (self dispose:))
					(else (localproc_033a) (localproc_04d7 local24 self))
				)
			)
			(5
				(cond 
					((< local25 0) (= local6 1) (self dispose:))
					(local25
						(if local21
							(self setScript: oppFall self)
						else
							(= local21 1)
							(= ticks 1)
						)
					)
					(local21 (= local21 0) (= local6 0) (= ticks 1))
					(else (= local6 1) (= ticks 1))
				)
			)
			(6 (self dispose:))
		)
	)
)

(instance oppsTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 10 30)))
			(1
				(if local18 (localproc_012e 0) (localproc_0219))
				(= local24 (Random 1 4))
				(localproc_04d7 local24 self)
				(= local19 0)
			)
			(2
				(localproc_0392 self)
				(proc471_7)
			)
			(3
				(localproc_012e 0)
				(if (ego script?) (= state (- state 1)))
				(= ticks 1)
			)
			(4
				(localproc_0219)
				(cond 
					(local18
						(= local27 -1)
						(= local18 0)
						(ego setScript: egoFallGetUp self)
					)
					((== local3 1) (= ticks 1))
					((== local19 0) (= local6 1) (self dispose:))
					(else (= local27 (!= local19 local24)) (= ticks 1))
				)
			)
			(5
				(cond 
					((< local27 0) (= local6 1) (self dispose:))
					(local27
						(if local20
							(ego setScript: egoFallGetUp self)
						else
							(= local20 1)
							(= local6 1)
							(self dispose:)
						)
					)
					(local20 (= local20 0) (= local6 1) (self dispose:))
					(else (= local6 0) (self dispose:))
				)
			)
			(6 (self dispose:))
		)
	)
)

(instance egoFallGetUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local6 1)
				(= local20 0)
				(localproc_01e4)
				(if (or (> (ego y?) 90) local18)
					(= ticks 1)
				else
					(self setScript: fallDown self)
				)
			)
			(1
				(cond 
					((== local30 3)
						(local1
							setScript: (if (== local2 0) endUhuraContest else endYesufuContest)
						)
						(self dispose:)
					)
					((and (== local2 0) (< [egoStats 17] 20)) (local1 setScript: egoTooTired))
					((== local2 1) (messager say: 8 6 50 0 self 470))
					(else (= ticks 1))
				)
			)
			(2
				(self setScript: (ScriptID 470 3) self)
			)
			(3 (self dispose:))
		)
	)
)

(instance egoTooTired of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_01e4)
				(HandsOff)
				(Bset 100)
				(= wrestledDay Day)
				(= cycles 10)
			)
			(1
				(messager say: 2 6 38 0 self 470)
			)
			(2
				(self setScript: oppDismount self)
			)
			(3
				(local1
					view: 971
					setScale: 200
					setLoop: -1
					setCycle: StopWalk 969
					setMotion: PolyPath (local1 x?) 250 self
				)
			)
			(4 (HandsOn) (local1 dispose:))
		)
	)
)

(instance oppDismount of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(local1 loop: 4 cel: 0)
				(= local7 0)
				(= cycles 1)
			)
			(1
				(local1
					cel: (++ local7)
					x: (+ (local1 x?) [local34 (* local7 2)])
					y: (+ (local1 y?) [local34 (+ (* local7 2) 1)])
				)
				(= ticks 6)
			)
			(2
				(if (== local7 8)
					(= cycles 1)
				else
					(self changeState: (-- state))
				)
			)
			(3
				(globalSound number: 920 setLoop: 1 play: 127)
				(self dispose:)
			)
		)
	)
)

(instance oppFall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ local29)
				(= local21 0)
				(localproc_01e4)
				(local1
					view: (if (== local2 1) local33 else local32)
					setLoop: 4
					cel: 0
				)
				(= local7 0)
				(= cycles 1)
			)
			(1
				(local1
					cel: (++ local7)
					x: (+ (local1 x?) [local34 (* local7 2)])
					y: (+ (local1 y?) [local34 (+ (* local7 2) 1)])
				)
				(= cycles 3)
			)
			(2
				(if (== local7 8)
					(= cycles 1)
					(globalSound number: 920 setLoop: 1 play: 127)
				else
					(self changeState: (-- state))
				)
			)
			(3
				(local1
					view: local31
					setScale: 200
					setLoop: -1
					setPri: -1
					setCycle: Walk
					setMotion: PolyPath 287 144 self
				)
				(if (== local2 1)
					(local1 setStep: 6 4)
				else
					(local1 setStep: 3 2)
				)
			)
			(4
				(cond 
					((== local2 1) (messager say: 8 6 49 0 self 470))
					((== local26 0) (++ local26) (messager say: 2 6 33 0 self 470))
					((== local26 1) (++ local26) (messager say: 2 6 34 0 self 470))
					(else (= ticks 1))
				)
			)
			(5
				(if (== local29 3)
					(local1
						setScript: (if (== local2 0) endUhuraContest else endYesufuContest)
					)
				else
					(local1
						view: local32
						setLoop: 0
						cel: 0
						setStep: 3 3
						setPri: 10
						setScale:
						scaleX: 109
						scaleY: 109
						setCycle: Fwd
						setMotion: MoveTo 287 132 self
					)
				)
			)
			(6
				(if (== local2 0)
					(local1 posn: 287 105)
				else
					(local1 posn: 293 79)
				)
				(local1 setLoop: 2 cel: 0 setCycle: End self)
			)
			(7
				(if (== local2 0)
					(local1 setLoop: 3 cel: 0 x: 292 y: 76 setCycle: End self)
				else
					(= ticks 1)
				)
			)
			(8
				(local1
					setLoop: -1
					loop: 1
					x: 295
					y: 76
					setStep: 4 2
					setCycle: Walk
					setMotion: MoveTo 160 76 self
				)
			)
			(9 (self dispose:))
		)
	)
)

(instance endUhuraContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_01e4)
				(HandsOff)
				(= cycles 1)
			)
			(1
				(messager say: 2 6 41 0 self 470)
			)
			(2
				(if (> local30 local29)
					(if (== (curRoom notify: 3) 4)
						(messager say: 2 6 43 0 self 470)
					else
						(Bset 100)
						(= wrestledDay Day)
						(if (== local30 3)
							(messager say: 2 6 39 0 self 470)
						else
							(messager say: 2 6 58 0 self 470)
						)
					)
				else
					(Bset 153)
					(if (== (curRoom notify: 3) 4)
						(messager say: 2 6 44 0 self 470)
					else
						(messager say: 2 6 40 0 self 470)
					)
				)
			)
			(3
				(if (< (local1 y?) 85)
					(self setScript: oppDismount self)
				else
					(= cycles 1)
				)
			)
			(4
				(local1
					view: 971
					setLoop: -1
					setScale: 200
					setCycle: StopWalk 969
					setMotion: PolyPath (local1 x?) 250 self
				)
			)
			(5
				(if (or (== local3 1) (> (ego y?) 90))
					(localproc_01e4)
					(HandsOn)
				else
					(proc471_7)
				)
				(self dispose:)
			)
		)
	)
)

(instance endYesufuContest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_01e4)
				(HandsOff)
				(if (> local30 local29)
					(Bset 76)
					(Bset global407)
					(messager say: 8 6 40 0 self 470)
				else
					(= global406 1)
					(ego solvePuzzle: 280 5 9)
					(messager say: 8 6 39 0 self 470)
				)
			)
			(1
				(if global406
					(cSound setLoop: 1 number: 462 play: 127 self)
				else
					(= cycles 1)
				)
			)
			(2
				(Bset 16)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance oppActs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register 1)
					(globalSound number: 922 setLoop: 1 play: 127)
				)
				(local1
					view: local33
					loop: [local8 register]
					cel: 0
					setCycle: End self
				)
			)
			(1 (= ticks 15))
			(2
				(localproc_04e9)
				(self dispose:)
			)
		)
	)
)

(instance egoActs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (IsObject local28) (local28 isKindOf: Timer))
					(= caller (local28 client?))
					(localproc_012e 0)
				)
				(if (== register 4)
					(globalSound number: 922 setLoop: 1 play: 127)
				)
				(ego
					view: 475
					loop: (- register 1)
					cel: 0
					setCycle: End self
				)
			)
			(1 (= ticks 18))
			(2 (ego setCycle: Beg self))
			(3 (localproc_043a self))
			(4 (self dispose:))
		)
	)
)

(instance thiefJumps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 940 setLoop: 1 play: 127)
				(ego view: 30 loop: 2 cel: 0 y: 75 setCycle: End self)
			)
			(1 (= ticks 6))
			(2
				(ego view: 12 loop: 0 x: (+ (ego x?) 12) y: 80)
				(localproc_043a self)
			)
			(3 (self dispose:))
		)
	)
)

(instance egoForward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego x?) 270)
					(localproc_01e4)
					(curRoom setScript: (ScriptID 470 2))
					(self dispose:)
				else
					(ego
						view: 12
						loop: 0
						cel: 0
						setMotion: MoveTo (+ (ego x?) (* (ego xStep?) 6)) (ego y?)
						setCycle: End self
					)
				)
			)
			(1
				(if (or (< [egoStats 2] 130) (< [egoStats 17] 20))
					(ego useSkill: 2 40)
					(proc470_1)
					(self setScript: fallDown self)
				else
					(ego useSkill: 2 2)
					(proc471_8)
					(= ticks 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance egoBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) 55)
					(proc471_8)
					(self dispose:)
				else
					(ego
						view: 12
						loop: 0
						cel: (ego lastCel:)
						setMotion: MoveTo (- (ego x?) (* (ego xStep?) 6)) (ego y?)
						setCycle: Beg self
					)
				)
			)
			(1
				(if (or (< [egoStats 2] 150) (< [egoStats 17] 20))
					(ego useSkill: 2 50)
					(self setScript: fallDown self)
				else
					(ego useSkill: 2 3)
					(proc471_8)
					(= ticks 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance quitCross of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 0)
				(ego view: 30 loop: 2 setCycle: 0 setPri: 11 cel: 0)
				(= cycles 1)
			)
			(1
				(ego
					cel: (++ local7)
					x: (+ (ego x?) [local34 (* local7 2)])
					y: (+ (ego y?) [local34 (+ (* local7 2) 1)])
				)
				(= cycles 10)
			)
			(2
				(if (== local7 8)
					(= cycles 1)
				else
					(self changeState: (-- state))
				)
			)
			(3
				(ego view: 0 normalize:)
				(if (and (IsObject local1) (< (local1 y?) 92))
					(++ local30)
					(local1
						setScript: (if (== local2 0) endUhuraContest else endYesufuContest)
					)
				else
					(HandsOn)
				)
				(curRoom notify: 1 0)
				(proc470_1)
				(self dispose:)
			)
		)
	)
)

(instance thiefControls of GloryControls
	(properties)
	
	(method (init)
		(= local0 1)
		(bridgeWin open:)
		(= icon1 iconJump)
		(= icon2 iconDrop)
		(= icon3 iconLeft)
		(= icon4 iconRight)
		(= icon5 iconBack)
		(= icon6 iconForward)
		(= icon7 iconQuit)
		(= window bridgeWin)
		(self
			add: iconRight iconLeft iconBack iconForward iconJump iconDrop iconQuit
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #cursor 999
			eachElementDo: #signal 129
		)
		(iconQuit signal: (| (iconQuit signal?) $0040))
		(self add: iconDummy enable: iconQuit)
		(super init: &rest)
	)
	
	(method (dispose)
		(= local0 0)
		(super dispose: &rest)
	)
	
	(method (show)
		(User input: 1)
		(theGame setCursor: 999)
		(super show: &rest)
	)
	
	(method (dispatchEvent event)
		(= gameTime (GetTime))
		(timers eachElementDo: #doit)
		(Animate (cast elements?) 1)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(super dispatchEvent: event)
	)
)

(class bridgeIcon of IconI
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
	)
	
	(method (onMe theObjOrX)
		(return
			(if (not (& signal $0004))
				(if
					(and
						(>= (theObjOrX x?) nsLeft)
						(>= (theObjOrX y?) nsTop)
						(<= (theObjOrX x?) nsRight)
						(<= (theObjOrX y?) nsBottom)
						(not
							(IsItSkip
								view
								loop
								cel
								(- (theObjOrX y?) nsTop)
								(- (theObjOrX x?) nsLeft)
							)
						)
					)
					(if (not cursor) (= cursor 1) (self highlight: 1))
					(return 1)
				else
					(if cursor (= cursor 0) (self highlight: 0))
					(return 0)
				)
			else
				(return 0)
			)
		)
	)
)

(instance bridgeControls of GloryControls
	(properties)
	
	(method (init)
		(= local0 1)
		(super init: &rest)
		(bridgeWin open:)
		(= icon1 iconJump)
		(= icon2 iconDrop)
		(= icon3 (= icon5 iconLeft))
		(= icon4 (= icon6 iconRight))
		(= icon7 iconQuit)
		(= window bridgeWin)
		(self
			add: iconRight iconLeft iconJump iconDrop iconQuit
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #cursor 999
			eachElementDo: #signal 129
		)
		(if (cast contains: (ScriptID 39 1))
			(self disable: iconQuit)
		)
		(iconQuit signal: (| (iconQuit signal?) $0040))
		(self add: iconDummy)
		(SetCursor 110 200 176 284)
	)
	
	(method (dispose)
		(= local0 0)
		(super dispose: &rest)
	)
	
	(method (show)
		(User input: 1)
		(theGame setCursor: 999 195 100)
		(super show: &rest)
	)
	
	(method (hide)
		(SetCursor -2)
		(super hide:)
	)
	
	(method (dispatchEvent event &tmp newEvent eventType eventMessage)
		(= local18 0)
		(= gameTime (+ tickOffset (GetTime)))
		(timers eachElementDo: #doit)
		(Animate (cast elements?) 1)
		(theDoits doit:)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(if (IsObject (curRoom script?))
			((curRoom script?) doit:)
		)
		(if fastCast
			(fastCast eachElementDo: #doit)
			(if
			(and ((= newEvent (Event new:)) type?) fastCast)
				(fastCast firstTrue: #handleEvent newEvent)
			)
			(newEvent dispose:)
			(= gameTime (+ tickOffset (GetTime)))
		)
		(return
			(if (& (= eventType (event type?)) $0040)
				(= eventType (& eventType $ffbf))
				(switch (event message?)
					(JOY_UP (return 0))
					(JOY_DOWN (return 0))
					(else 
						(super dispatchEvent: event)
					)
				)
			else
				(= eventMessage (event message?))
				(if (== eventType evKEYBOARD)
					(cond 
						((== eventMessage KEY_8) (return 0))
						((== eventMessage KEY_2) (return 0))
						(else (super dispatchEvent: event))
					)
				else
					(super dispatchEvent: event)
				)
			)
		)
	)
)

(instance iconJump of bridgeIcon
	(properties
		view 470
		loop 1
		cel 0
		nsLeft 2
		nsTop 2
		maskView 470
		maskLoop 10
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(= local19 4)
				(if (== origHeroType 2)
					(localproc_02ae)
					(ego setScript: thiefJumps)
				else
					(localproc_0219)
					(ego setScript: egoActs 0 local19)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconRight of bridgeIcon
	(properties
		view 470
		loop 4
		cel 0
		nsLeft 52
		nsTop 34
		maskView 470
		maskLoop 10
		maskCel 3
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(= local19 3)
				(if (== origHeroType 2)
					(localproc_02ae)
				else
					(localproc_0219)
				)
				(ego setScript: egoActs 0 local19)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconLeft of bridgeIcon
	(properties
		view 470
		loop 3
		cel 0
		nsLeft 2
		nsTop 34
		maskView 470
		maskLoop 10
		maskCel 2
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(= local19 2)
				(if (== origHeroType 2)
					(localproc_02ae)
				else
					(localproc_0219)
				)
				(ego setScript: egoActs 0 local19)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconDrop of bridgeIcon
	(properties
		view 470
		loop 2
		cel 0
		nsLeft 50
		nsTop 2
		maskView 470
		maskLoop 10
		maskCel 1
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(if (== origHeroType 2)
					(localproc_02ae)
				else
					(localproc_0219)
				)
				(= local19 1)
				(ego setScript: egoActs 0 local19)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconForward of bridgeIcon
	(properties
		view 470
		loop 9
		cel 0
		nsLeft 60
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 5
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_02ae)
				(ego setScript: egoForward)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconBack of bridgeIcon
	(properties
		view 470
		loop 8
		cel 0
		nsLeft 3
		nsTop 12
		maskView 470
		maskLoop 10
		maskCel 4
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_02ae)
				(ego setScript: egoBack)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconQuit of bridgeIcon
	(properties
		view 470
		loop 5
		cel 0
		nsLeft 24
		nsTop 24
		maskView 470
		maskLoop 10
		maskCel 6
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(localproc_01e4)
				(proc470_1)
				(ego setScript: quitCross)
				(return 1)
			else
				0
			)
		)
	)
)

(instance iconDummy of IconI
	(properties
		nsLeft 20
		nsTop 20
		nsRight 21
		nsBottom 21
		cursor 997
		signal $0080
	)
	
	(method (show)
	)
	
	(method (select)
		(return 1)
	)
	
	(method (highlight)
	)
)

(instance bridgeWin of Window
	(properties
		top 100
		left 200
		bottom 166
		right 284
		back 28
	)
	
	(method (open)
		(if window (SetPort window) else (super open: &rest))
		(DrawCel 470 0 0 0 0 -1)
		(DrawCel 470 6 0 3 2 -1)
	)
)
