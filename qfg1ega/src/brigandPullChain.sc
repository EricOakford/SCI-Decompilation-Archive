;;; Sierra Script 1.0 - (do not remove this comment)
(script# 231)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	pullChain 0
	takeADive 1
)

(instance pullChain of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 231)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: vEgoGrab posn: 34 73 setLoop: 2 setCel: 0)
				(= cycles 3)
			)
			(1
				(ego setCel: 1)
				((ScriptID 96 14) setCel: 7)
				(= cycles 3)
			)
			(2
				(ego setCel: 0)
				((ScriptID 96 14) setCel: 6)
				(= cycles 3)
			)
			(3
				(ego setCel: 1)
				((ScriptID 96 14) setCel: 7)
				(= cycles 3)
			)
			(4
				(NormalEgo)
				(ego setPri: 7 loop: 0)
				((ScriptID 96 14) setCel: 6)
				(if (not (Btst fPulledChain))
					(Bset fPulledChain)
					((ScriptID 96 6) dispose:)
					((ScriptID 96 5) setLoop: 5)
				)
				(if (not (client cel?))
					(client setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance takeADive of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 231)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setCycle: EndLoop self)
			)
			(1
				(if (not (Btst fPulledChain))
					((ScriptID 96 6) dispose:)
				)
				(if (Btst fBefriendedYorick)
					(HighPrint 231 0)
					;"Elsa is right next door.  I'll go set up the secret exit for your escape."
				)
				((ScriptID 96 5) view: vYorickLeave setLoop: 1 setCel: 0)
				(= cycles 2)
			)
			(2
				((ScriptID 96 5) setCel: 1)
				(= cycles 1)
			)
			(3
				((ScriptID 96 5) setCel: 2)
				(= cycles 1)
			)
			(4
				((ScriptID 96 5)
					setCel: 3
					y: (- ((ScriptID 96 5) y?) 10)
				)
				(= cycles 1)
			)
			(5
				((ScriptID 96 5)
					setCel: 4
					y: (- ((ScriptID 96 5) y?) 14)
				)
				(= cycles 1)
			)
			(6
				((ScriptID 96 5) dispose:)
				(client setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
