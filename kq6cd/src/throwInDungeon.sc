;;; Sierra Script 1.0 - (do not remove this comment)
(script# 821)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use DPath)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	throwInDungeon 0
)

(local
	local0
	local1
)
(instance throwInDungeon of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 821)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) dungeonEntered: 3)
				(ego
					normal: 0
					view: 825
					setLoop: 0
					cel: 0
					posn: 100 144
					setPri: 9
					setCycle: 0
					cycleSpeed: 8
					moveSpeed: 0
					setScale:
					scaleX: 121
					scaleY: 121
					setMotion: JumpTo 150 154 self
				)
			)
			(1 (ego setCycle: End self))
			(2
				(soundFx2 number: 960 setLoop: 1 play: self)
			)
			(3
				(if (not ((ScriptID 80 0) tstFlag: 711 64))
					(messager say: 1 0 8 0 self)
				else
					(= cycles 1)
				)
			)
			(4
				(if (not ((ScriptID 80 0) tstFlag: 711 64))
					(soundFx2 number: 902 loop: 1 play:)
					((ScriptID 820 3) setCycle: Beg self)
				else
					(= state (+ state 2))
					(= cycles 1)
				)
			)
			(5
				(soundFx2 number: 822 loop: 1 play: self)
			)
			(6 (= cycles 1))
			(7
				(= local0 0)
				(cond 
					(((ScriptID 80 0) tstFlag: 709 128) (= state 9) (self setScript: beautyEntrance self))
					(((ScriptID 80 0) tstFlag: 709 4096)
						((ScriptID 80 0) clrFlag: 709 4096)
						(self setScript: searchEgo self)
					)
					(((ScriptID 80 0) tstFlag: 711 32)
						((ScriptID 80 0) clrFlag: 711 32)
						(= local0 (ego has: 44))
						((ScriptID 820 3) setPri: -1 stopUpd:)
						(self setScript: afterClownHelpedEgoEscape self)
					)
					(
						(and
							(Btst 10)
							(not ((ScriptID 80 0) tstFlag: 709 16384))
						)
						((ScriptID 80 0) setFlag: 709 16384 1)
						((ScriptID 80 0) setFlag: 711 32)
						(self register: jolloHelpsEgo setScript: jolloHelpsEgo)
					)
					((ego has: 44) (= local0 1) (= cycles 1))
					(else (= cycles 1))
				)
			)
			(8
				((ScriptID 80 0) setFlag: 711 64)
				(if (not register) (= register self))
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(9
				(if local0 (= state 11))
				(ego reset: 7 posn: 132 149)
				(if (!= register self)
					(register cycles: 1)
				else
					(= cycles 1)
				)
			)
			(10 (= cycles 3))
			(11
				(curRoom setScript: (ScriptID 820 1))
			)
			(12
				(theGame handsOn:)
				((ScriptID 820 3) setPri: -1 stopUpd:)
				(ego reset: 1)
				(self dispose:)
			)
		)
	)
)

(instance searchEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 44 820)
				(messager say: 1 0 13 0 self)
			)
			(1
				((ScriptID 820 3) setCycle: Beg self)
			)
			(2
				(soundFx2 number: 822 loop: 1 play:)
				((ScriptID 820 3) setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance beautyEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 1 2 self))
			(1
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(ego reset: 7 posn: 132 149)
				(= cycles 4)
			)
			(3
				(messager say: 1 0 1 3 self oneOnly: 0)
			)
			(4 (= cycles 4))
			(5
				((ScriptID 820 3) setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance afterClownHelpedEgoEscape of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 0 11 0 self)
			)
			(1
				((ScriptID 820 3) setCycle: Beg self)
			)
			(2
				(soundFx2 number: 822 loop: 1 play:)
				(self dispose:)
			)
		)
	)
)

(instance jolloHelpsEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 9 1 self))
			(1 (client cue:))
			(2 (= seconds 5))
			(3
				(soundFx2 number: 821 loop: 1 play: self)
			)
			(4
				(soundFx2 number: 821 loop: 1 play:)
				((ScriptID 820 3) setCycle: End self)
			)
			(5
				(soundFx2 stop:)
				(jollo
					ignoreActors:
					init:
					cel: 0
					cycleSpeed: 8
					setLoop: 0
					setCycle: End self
				)
			)
			(6
				(jollo posn: 87 148 setLoop: 1 cel: 0 setCycle: End self)
			)
			(7 (messager say: 1 0 9 2 self))
			(8 (Face ego jollo self))
			(9 (= cycles 5))
			(10
				(messager say: 1 0 9 3 self oneOnly: 0)
			)
			(11
				(jollo
					posn: 100 150
					setLoop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(12
				(ego
					reset: 1
					setPri: 1
					setMotion: DPath 93 142 34 142 self
				)
			)
			(13
				(jollo setCycle: 0)
				(curRoom newRoom: 710)
			)
		)
	)
)

(instance jollo of Prop
	(properties
		x 89
		y 147
		view 822
		priority 9
		signal $0010
	)
)
