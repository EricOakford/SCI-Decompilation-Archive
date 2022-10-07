;;; Sierra Script 1.0 - (do not remove this comment)
(script# 822)
(include sci.sh)
(use Main)
(use KQ6Print)
(use Kq6Procs)
(use RandCyc)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	boyGhostScript 0
	boyGhost 1
)

(local
	local0
	local1
	local2
	local3
	local4
)
(procedure (localproc_0b12)
	(if ((ScriptID 820 4) cycler?)
		((ScriptID 820 4) setCycle: 0 stopUpd:)
		((ScriptID 820 5) setCycle: 0 stopUpd:)
	)
)

(procedure (localproc_0b4d)
	(if (not ((ScriptID 820 4) cycler?))
		((ScriptID 820 4) startUpd: setCycle: Forward)
		((ScriptID 820 5) startUpd: setCycle: RandCycle)
	)
)

(instance boyGhostScript of Script
	(properties)
	
	(method (dispose)
		(theGame handsOn:)
		(super dispose:)
		(boyGhost delete:)
		(DisposeScript 822)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 763)
				(= cycles 5)
			)
			(1
				(theGame handsOff:)
				(boyGhost
					view: 763
					setLoop: 0
					cel: 7
					x: 260
					y: 118
					cycleSpeed: 10
					show:
					setCycle: EndLoop self
				)
			)
			(2
				(boyGhost setLoop: 1 cel: 7 posn: 258 117)
				(self setScript: cryBaby self)
			)
			(3
				(if (ego has: 17)
					(theGame handsOn:)
				else
					(= state 5)
					(= cycles 1)
				)
			)
			(4
				(theGame handsOff:)
				(messager say: 6 2 16 1 self)
			)
			(5
				(localproc_0b4d)
				(cryBaby
					next: (showNonHanky register: 1 caller: self yourself:)
				)
			)
			(6
				(if (or local0 (ego has: 17))
					(localproc_0b12)
					(= state 7)
					(if local0 (= cycles 3) else (theGame handsOn:))
				else
					(self setScript: noHanky self)
				)
			)
			(7
				(curRoom setScript: (ScriptID 820 1))
			)
			(8
				(theGame handsOff:)
				((ScriptID 80 0) setFlag: 709 -32768)
				(messager say: 6 50 0 1 self)
			)
			(9
				(localproc_0b4d)
				(if (== script cryBaby)
					(cryBaby next: (showNonHanky caller: self yourself:))
				else
					(= cycles 1)
				)
			)
			(10
				(ego
					normal: 0
					setScale: 0
					view: 763
					setLoop: 7
					cel: 0
					setCycle: EndLoop self
				)
			)
			(11
				(boyGhost setCycle: EndLoop self)
			)
			(12
				(boyGhost setLoop: 3 cel: 0 posn: 229 70 setCycle: Forward)
				(= seconds 5)
			)
			(13
				(localproc_0b12)
				(= cycles 4)
			)
			(14
				((ScriptID 820 2)
					add: -1 6 50 0 2
					add: -1 6 50 0 3
					add: -1 6 50 0 4
					init: self
				)
			)
			(15
				(localproc_0b4d)
				(boyGhost setCycle: BegLoop self)
			)
			(16
				(boyGhost
					setLoop: 4
					cel: 0
					posn: 155 88
					setCycle: CycleTo 10 1 self
				)
			)
			(17
				(ego reset: 0)
				(boyGhost setCycle: EndLoop self)
			)
			(18
				(boyGhost
					setLoop: 5
					cel: 0
					posn: 229 88
					setCycle: EndLoop self
				)
			)
			(19
				(boyGhost hide:)
				(= seconds 2)
			)
			(20
				(boyGhost
					setLoop: 6
					setPri: (- (ego priority?) 1)
					cel: 0
					posn: 85 151
					show:
					setCycle: EndLoop self
				)
			)
			(21
				(messager say: 6 50 0 5 self)
			)
			(22
				(theMusic fadeTo: 824 -1)
				(boyGhost setCycle: 0 setMotion: 0 dispose:)
			)
		)
	)
)

(instance showNonHanky of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_0b4d)
				(if (and (== (boyGhost loop?) 1) (boyGhost cel?))
					(boyGhost setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(boyGhost
					setLoop: 2
					cel: 0
					posn: 241 96
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(if register
					(localproc_0b12)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(3
				(if register
					((ScriptID 820 2)
						add: -1 6 2 16 2
						add: -1 6 2 16 3
						init: self
					)
				else
					(= ticks 1)
				)
			)
			(4
				(if register (localproc_0b4d))
				(if local0 (self dispose:) else (= seconds 3))
			)
			(5
				(boyGhost setCycle: CycleTo 0 -1 self)
			)
			(6
				(boyGhost setLoop: 1 cel: 0 posn: 258 117)
				(= local1 0)
				(if (not register)
					(theGame handsOn:)
					(client setScript: cryBaby)
				else
					(= register 0)
					(client setScript: cryBaby 0 1)
				)
			)
		)
	)
)

(instance cryBaby of Script
	(properties)
	
	(method (init)
		(= local2 30)
		(super init: &rest)
	)
	
	(method (doit)
		(= local3 (GetTime 1))
		(if
			(and
				(not local1)
				(!= local4 local3)
				(== curRoomNum newRoomNum)
			)
			(= local4 local3)
			(if (< local2 30) (++ local2))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(if (not next)
			(switch (= state newState)
				(0
					(boyGhost view: 763 setLoop: 1 x: 258 y: 117)
					(if (boyGhost cel?)
						(boyGhost setCycle: BegLoop self)
					else
						(= cycles 1)
					)
				)
				(1
					(cond 
						((== register -32768) (messager say: 6 0 0 2 self oneOnly: 0))
						(register (messager say: 6 2 16 4 self))
						((not local1) (messager say: 1 0 4 0 self))
						(else (= ticks 1))
					)
					(= register 0)
				)
				(2
					(localproc_0b4d)
					(if (and (not local1) (== local2 30)) (= local2 0))
					(if caller (caller cue:) (= caller 0))
					(boyGhost setCycle: EndLoop self)
				)
				(3
					(boyGhost setCycle: CycleTo 2 -1 self)
				)
				(4
					(if (and (not local1) (== local2 30))
						(= state -1)
						(= cycles 1)
					else
						(= state 1)
						(= cycles (Random 1 15))
					)
				)
			)
		else
			(self dispose:)
		)
	)
)

(instance noHanky of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(KQ6Print posn: -1 10 say: 0 1 0 5 1 init: self)
			)
			(1
				(self setScript: justCryToMama self 2)
			)
			(2
				(localproc_0b12)
				(= cycles 2)
			)
			(3
				((ScriptID 820 2)
					add: -1 1 0 5 3 -1 10
					add: -1 1 0 5 4 -1 10
					init: self
				)
			)
			(4
				(localproc_0b4d)
				(self setScript: justCryToMama self 5)
			)
			(5
				(Face ego (ScriptID 820 3) self)
			)
			(6
				(localproc_0b12)
				(= cycles 4)
			)
			(7 (messager say: 1 0 5 6 self))
			(8 (messager say: 1 0 5 7 self))
			(9
				(localproc_0b4d)
				(boyGhost
					setLoop: 0
					cel: 7
					x: 260
					y: 118
					setCycle: BegLoop self
				)
			)
			(10
				(if (<= (ego x?) ((ScriptID 820 3) approachX?))
					(ego
						setMotion:
							PolyPath
							((ScriptID 820 3) approachX?)
							((ScriptID 820 3) approachY?)
							self
					)
				else
					(++ state)
					(= cycles 1)
				)
			)
			(11
				(Face ego (ScriptID 820 3) self)
			)
			(12
				(ego setPri: 9)
				(soundFx2 number: 821 loop: 1 play:)
				((ScriptID 820 3) setPri: 10 setCycle: EndLoop self)
			)
			(13
				(localproc_0b12)
				((ScriptID 820 3) stopUpd:)
				(= cycles 4)
			)
			(14
				(messager say: 1 0 5 8 self)
			)
			(15
				(soundFx2 stop:)
				((ScriptID 820 2)
					add: -1 1 0 5 9
					add: -1 1 0 5 10
					add: -1 1 0 5 11
					add: -1 1 0 5 12
					add: -1 1 0 5 13
					init: self
				)
			)
			(16
				(localproc_0b4d)
				((ScriptID 820 3) setCycle: BegLoop self)
				(soundFx2 number: 822 loop: 1 play:)
			)
			(17
				(soundFx2 stop:)
				(self dispose:)
			)
		)
	)
)

(instance justCryToMama of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boyGhost view: 763 setLoop: 1 x: 258 y: 117)
				(if (boyGhost cel?)
					(boyGhost setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(KQ6Print posn: -1 10 say: 0 1 0 5 register init: self)
			)
			(2
				(boyGhost setCycle: EndLoop self)
			)
			(3 (self dispose:))
		)
	)
)

(instance boyGhost of Actor
	(properties
		x 260
		y 118
		noun 6
		approachX 98
		approachY 149
		view 763
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self hide: setScript: boyGhostScript approachVerbs: 50)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (== (boyGhostScript state?) 3)
					(= local1 1)
					(boyGhostScript cue:)
				else
					(localproc_0b12)
					(messager say: noun theVerb 17)
					(localproc_0b4d)
				)
			)
			(50
				(localproc_0b12)
				(= local0 1)
				(ego put: 17 820)
				(theGame givePoints: 3)
				(= local1 1)
				(if (== (boyGhostScript state?) 3)
					(boyGhostScript cue:)
				else
					(boyGhostScript changeState: 8)
				)
			)
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(localproc_0b12)
					(messager say: noun 0 0 1)
					(= local1 1)
					(if (!= (script script?) showNonHanky)
						((script script?) register: -32768 next: showNonHanky)
					)
				else
					(super doVerb: theVerb)
				)
			)
		)
	)
)
