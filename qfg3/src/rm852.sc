;;; Sierra Script 1.0 - (do not remove this comment)
(script# 852)
(include sci.sh)
(use Main)
(use EgoDead)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm852 0
	paladinEndScript 1
	crankUpSword 2
	chuckSwordAtWiz 3
	lightnin1 4
	lightnin2 5
	orbTempts 6
)

(instance rm852 of Rm
	(properties
		modNum 850
		noun 18
		picture 850
	)
	
	(method (init)
		(= heroType 3)
		(Bset 124)
		(super init:)
		(self setRegions: 850)
		(if (== prevRoomNum 550)
			(self setScript: killedGarg)
		else
			(self setScript: paladinEndScript)
		)
		(if (not (== global155 0)) (theGame save: 1))
	)
)

(instance paladinEndScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 2) view: 863)
				(ego setMotion: MoveTo 20 183 self)
			)
			(1
				(if (Btst 150)
					(messager say: 2 6 62 0 self 850)
				else
					(self cue:)
				)
			)
			(2
				(messager say: 3 6 13 0 self 850)
			)
			(3
				((ScriptID 850 2) view: 863 loop: 6 setCycle: End self)
			)
			(4
				((ScriptID 850 3) setCycle: End self)
			)
			(5
				((ScriptID 850 3) loop: 1 setCycle: CT 3 1 self)
			)
			(6
				(= monsterNum 855)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance killedGarg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 3) loop: 2 cel: 0 noun: 4)
				(ego x: 20 y: 183 solvePuzzle: 342 3 9)
				((ScriptID 850 2) view: 863 loop: (Random 0 6) cel: 0)
				(= seconds 2)
			)
			(1
				(if (== global155 0) (EgoDead 59 850) else (self cue:))
			)
			(2
				((ScriptID 850 3) cycleSpeed: 3 setCycle: End self)
			)
			(3
				((ScriptID 850 3) view: 854 setLoop: 1)
				(messager say: 3 6 7 0 self 850)
			)
			(4
				((ScriptID 850 2) setScript: (ScriptID 850 8))
				(HandsOn)
				(theIconBar disable: 1 5 6)
				(self dispose:)
			)
		)
	)
)

(instance crankUpSword of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (self script?) (ScriptID 850 12))
				(== ((ScriptID 850 12) state?) 0)
				(== (self state?) 0)
			)
			((ScriptID 850 12) cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr 82)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: 1 4 5 6)
				(Bset 89)
				((ScriptID 850 8) dispose:)
				(ego setMotion: MoveTo 42 178 self)
			)
			(1
				(theIconBar disable: 1 4 5 6)
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego setMotion: 0 setCycle: 0)
				(Face ego (ScriptID 850 2))
				((ScriptID 850 2)
					view: 863
					cel: 0
					loop: (Random 0 6)
					setCycle: End self
				)
			)
			(2
				((ScriptID 850 2)
					view: 863
					loop: 0
					setCycle: CT 6 1 self
				)
			)
			(3
				((ScriptID 850 9)
					setLoop: (Random 0 4)
					x: 186
					y: 60
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					setStep: 8 7
					init:
					setMotion: MoveTo 61 111 self
				)
				((ScriptID 850 2) setCycle: End self)
			)
			(4
				(ego view: 38 loop: 0 cel: 0 setCycle: End)
				(sFx2 number: 240 play:)
			)
			(5
				(sFx2 number: 10 play:)
				((ScriptID 850 9) dispose:)
				(ego setCycle: Beg self)
			)
			(6
				(ego normalize:)
				(= state 0)
				(if (== (self script?) (ScriptID 850 12))
					((ScriptID 850 12) cue:)
				)
				(if
				(== ((ScriptID 850 3) script?) (ScriptID 850 7))
					((ScriptID 850 7) cue:)
				)
				(theIconBar enable: 1 4)
				(switch arcadeDifficulty
					(1 (= seconds 15))
					(2 (= seconds 10))
					(3 (= seconds 5))
				)
			)
		)
	)
)

(instance chuckSwordAtWiz of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 8) dispose:)
				(crankUpSword dispose:)
				(self setScript: (ScriptID 32 0) self 11)
				(ego drop: 1)
				(Bset 91)
			)
			(1)
			(2
				(sFx2 number: 910 play:)
				((ScriptID 850 2)
					view: 865
					loop: 0
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
				(if (== (ego script?) (ScriptID 850 11))
					((ScriptID 850 11) cue:)
				)
				(if (== ((ScriptID 850 16) state?) 0)
					((ScriptID 850 16) cue:)
				)
			)
			(3
				((ScriptID 850 2)
					loop: 1
					cel: 0
					setPri: 14
					setCycle: End self
				)
			)
			(4
				((ScriptID 850 2)
					view: 865
					setLoop: 3
					cycleSpeed: 0
					setPri: 2
					setCycle: Fwd
				)
				(lightnin2 init: setPri: 1 cycleSpeed: 0 setCycle: Fwd)
				(sFx2 number: 7 play:)
				(self setScript: (ScriptID 850 10))
				((ScriptID 850 10) setScript: orbTempts)
				(if (== (ego script?) (ScriptID 850 11))
					(messager say: 2 6 31 0 0 850)
					((ScriptID 850 11) cue:)
				)
				(if (== ((ScriptID 850 16) state?) 0)
					((ScriptID 850 16) cue:)
				)
			)
		)
	)
)

(instance orbTempts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(messager say: 2 6 32 0 self 850)
			)
			(2 (= seconds 7))
			(3
				(messager say: 2 6 51 0 self 850)
			)
			(4 (= seconds 7))
			(5
				(messager say: 2 6 52 0 self 850)
			)
			(6 (= seconds 7))
			(7
				(messager say: 2 6 53 0 self 850)
			)
			(8
				(messager say: 2 6 54 0 self 850)
			)
			(9 (= seconds 7))
			(10
				(messager say: 2 6 55 0 self 850)
			)
			(11 (self dispose:))
		)
	)
)

(instance lightnin1 of Prop
	(properties
		x 265
		view 855
		loop 1
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		((ScriptID 850 4) doVerb: theVerb)
	)
)

(instance lightnin2 of Prop
	(properties
		x 204
		view 855
		loop 1
		cel 1
		priority 13
		signal $4010
	)
)

(instance sFx2 of Sound
	(properties)
)
