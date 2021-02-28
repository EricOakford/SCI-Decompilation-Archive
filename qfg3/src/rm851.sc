;;; Sierra Script 1.0 - (do not remove this comment)
(script# 851)
(include sci.sh)
(use Main)
(use EgoDead)
(use Sound)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm851 0
	fighterEndScript 1
	crankUpShield 2
	spearWiz 3
)

(instance rm851 of Rm
	(properties
		modNum 850
		noun 18
		picture 850
	)
	
	(method (init)
		(super init:)
		(self setRegions: 850)
		(Bset 124)
		(if (== prevRoomNum 550)
			(self setScript: killedGarg)
		else
			(self setScript: fighterEndScript)
		)
		(if (not (== global155 0)) (theGame save: 1))
	)
)

(instance fighterEndScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 6) init: stopUpd:)
				(ego setMotion: MoveTo 20 183 self)
			)
			(1
				(messager say: 1 6 4 0 self 850)
			)
			(2
				(messager say: 1 6 5 0 self 850)
			)
			(3
				((ScriptID 850 6) dispose:)
				(DrawPic 850 dpOPEN_PIXELATION)
				(= seconds 2)
			)
			(4
				((ScriptID 850 2) loop: 1 setCycle: End self)
			)
			(5
				(messager say: 3 6 6 0 self 850)
			)
			(6
				((ScriptID 850 2) view: 863 loop: 6 setCycle: End self)
			)
			(7
				(sFx2 number: 944 play:)
				((ScriptID 850 3) setCycle: End self)
			)
			(8
				((ScriptID 850 3) loop: 1 setCycle: CT 3 1 self)
			)
			(9
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
				(ego normalize: x: 20 y: 183 solvePuzzle: 342 3)
				((ScriptID 850 3) loop: 2 cel: 0 noun: 4)
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

(instance crankUpShield of Script
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
				(Bset 82)
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
				(sFx2 number: 13 play:)
				((ScriptID 850 9)
					setLoop: (Random 0 4)
					x: 186
					y: 60
					cycleSpeed: 0
					moveSpeed: 0
					setScale:
					setStep: 8 7
					init:
					setMotion: MoveTo (+ (ego x?) 20) (- (ego y?) 34) self
				)
				((ScriptID 850 2) setCycle: End self)
			)
			(4
				(ego view: 36 loop: 0 cel: 0 setCycle: CT 4 1)
			)
			(5
				(sFx2 number: 10 play:)
				((ScriptID 850 9) dispose:)
				(ego setCycle: End self)
			)
			(6
				(ego normalize:)
				(if (== (self script?) (ScriptID 850 12))
					((ScriptID 850 12) cue:)
				)
				(if
				(== ((ScriptID 850 3) script?) (ScriptID 850 7))
					((ScriptID 850 7) cue:)
				)
				(= state 0)
				(HandsOn)
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

(instance spearWiz of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (== ((ScriptID 850 16) state?) -1))
					0
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(Bset 84)
				(if ((ScriptID 850 2) script?)
					((ScriptID 850 2) setScript: 0)
				)
				((ScriptID 32 0) start: 1)
				(AutoTarget
					((User curEvent?) x?)
					((User curEvent?) y?)
				)
				(self setScript: (ScriptID 32 0) 0 56)
				(ego drop: 45)
				(theIconBar advanceCurIcon:)
			)
			(2
				(sFx2 number: 931 play: 127)
				((ScriptID 850 2)
					setPri: 14
					moveSpeed: 0
					setStep: 6 5
					setCycle: Walk
					setMotion: MoveTo 243 89 self
				)
				((ScriptID 850 5) setPri: 0)
			)
			(3
				(sFx2 number: 101 play:)
				((ScriptID 850 2) dispose:)
				(ego solvePuzzle: 340 10)
				(= seconds 1)
			)
			(4
				(if (== (ego script?) (ScriptID 850 11))
					(messager say: 2 6 31 0 0 850)
					((ScriptID 850 11) cue:)
				)
				(if (== ((ScriptID 850 16) state?) 0)
					((ScriptID 850 16) cue:)
				)
				(self setScript: (ScriptID 850 10))
			)
		)
	)
)

(instance sFx2 of Sound
	(properties)
)
