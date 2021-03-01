;;; Sierra Script 1.0 - (do not remove this comment)
(script# 851)
(include game.sh) (include "850.shm")
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

(instance rm851 of Room
	(properties
		modNum 850
		noun N_ROOM
		picture 850
	)
	
	(method (init)
		(super init:)
		(self setRegions: 850)
		(Bset fWizNoticesEgo)
		(if (== prevRoomNum 550)
			(self setScript: killedGarg)
		else
			(self setScript: fighterEndScript)
		)
		(if (not (== global155 0))
			(theGame save: TRUE)
		)
	)
)

(instance fighterEndScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 850 6) init: stopUpd:)
				(ego setMotion: MoveTo 20 183 self)
			)
			(1
				(messager say: N_DELORD V_DOIT C_DEMONS_TALK 0 self 850)
			)
			(2
				(messager say: N_DELORD V_DOIT C_NOTICE_EGO 0 self 850)
			)
			(3
				((ScriptID 850 6) dispose:)
				(DrawPic 850 PIXELDISSOLVE)
				(= seconds 2)
			)
			(4
				((ScriptID 850 2) loop: 1 setCycle: EndLoop self)
			)
			(5
				(messager say: N_DEWIZ V_DOIT C_NOTICE_EGO2 0 self 850)
			)
			(6
				((ScriptID 850 2) view: 863 loop: 6 setCycle: EndLoop self)
			)
			(7
				(sFx2 number: 944 play:)
				((ScriptID 850 3) setCycle: EndLoop self)
			)
			(8
				((ScriptID 850 3) loop: 1 setCycle: CycleTo 3 1 self)
			)
			(9
				(= monsterNum 855)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance killedGarg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego normalize: x: 20 y: 183 solvePuzzle: fBeatGargoyle 3)
				((ScriptID 850 3) loop: 2 cel: 0 noun: N_GARGOYLE)
				((ScriptID 850 2) view: 863 loop: (Random 0 6) cel: 0)
				(= seconds 2)
			)
			(1
				(if (== global155 0)
					(EgoDead C_DEATH_GARGOYLE 850)
				else
					(self cue:)
				)
			)
			(2
				((ScriptID 850 3) cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3
				((ScriptID 850 3) view: 854 setLoop: 1)
				(messager say: 3 6 7 0 self 850)
			)
			(4
				((ScriptID 850 2) setScript: (ScriptID 850 8))
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_ACTIONS ICON_CAST)
				(self dispose:)
			)
		)
	)
)

(instance crankUpShield of Script
	
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
		(Bclr fDeWizBattle)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ACTIONS ICON_CAST)
				(Bset fDeWizBattle)
				((ScriptID 850 8) dispose:)
				(ego setMotion: MoveTo 42 178 self)
			)
			(1
				(theIconBar disable: ICON_WALK ICON_TALK ICON_ACTIONS ICON_CAST)
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
					setCycle: EndLoop self
				)
			)
			(2
				((ScriptID 850 2)
					view: 863
					loop: 0
					setCycle: CycleTo 6 1 self
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
				((ScriptID 850 2) setCycle: EndLoop self)
			)
			(4
				(ego view: 36 loop: 0 cel: 0 setCycle: CycleTo 4 1)
			)
			(5
				(sFx2 number: 10 play:)
				((ScriptID 850 9) dispose:)
				(ego setCycle: EndLoop self)
			)
			(6
				(ego normalize:)
				(if (== (self script?) (ScriptID 850 12))
					((ScriptID 850 12) cue:)
				)
				(if (== ((ScriptID 850 3) script?) (ScriptID 850 7))
					((ScriptID 850 7) cue:)
				)
				(= state 0)
				(HandsOn)
				(theIconBar enable: ICON_WALK ICON_TALK)
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
				(Bset fSpearedWiz)
				(if ((ScriptID 850 2) script?)
					((ScriptID 850 2) setScript: 0)
				)
				((ScriptID 32 0) start: 1)
				(AutoTarget
					((User curEvent?) x?)
					((User curEvent?) y?)
				)
				(self setScript: (ScriptID 32 0) 0 56)
				(ego drop: iMagicSpear)
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
				(ego solvePuzzle: fBeatDeWiz 10)
				(= seconds 1)
			)
			(4
				(if (== (ego script?) (ScriptID 850 11))
					(messager say: N_REGION V_DOIT C_GARGOYLE_RELEASES 0 0 850)
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

(instance sFx2 of Sound)
