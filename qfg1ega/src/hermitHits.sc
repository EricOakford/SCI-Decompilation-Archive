;;; Sierra Script 1.0 - (do not remove this comment)
(script# 139)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	hermitHits 0
)

(local
	hermitHeadMove
)
(procedure (HenrySays &tmp [str 200])
	(Format @str &rest)
	(Print @str #at 160 10)
)

(instance hermitHits of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 139)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fKnockedOnHenryDoor)
					(Bclr fKnockedOnHenryDoor)
					(HenrySays 139 0)
					;"Please move away\nfrom the door."
					(User canControl: TRUE)
					(= cycles 15)
				else
					(self cue:)
				)
			)
			(1
				(if (not (ego script?))
					(HandsOff)
				)
				(Bset fHenryDoorOpen)
				(client
					view: vHenryDoor
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(cond 
					((== (ego onControl: origin) cLRED)
						((ScriptID 82 4)
							number: (SoundFX 84)
							loop: 1
							play:
						)
						(Bset fEgoKnockedOffCliff)
						(ego setScript: (ScriptID 138 0))
					)
					((== (ego onControl: origin) cYELLOW)
						(Bset fEgoSquashed)
						(ego setPri: 0)
					)
				)
				(client setCycle: EndLoop self)
			)
			(3
				(if (Btst fEgoSquashed)
					((ScriptID 82 4)
						number: (SoundFX 84)
						loop: 1
						play:
					)
					(ego hide:)
				)
				((ScriptID 82 1)
					posn: (- (client x?) 26) (- (client y?) 16)
					setCel: 0
					setPri: 15
					init:
				)
				(= cycles 5)
			)
			(4
				((ScriptID 82 1) setCel: 1)
				(= cycles 5)
			)
			(5
				(if (and (Btst fClimbedHenryCliff) (not (Btst fEgoKnockedOffCliff)) (not (Btst fEgoSquashed)))
					(Bclr fClimbedHenryCliff)
					(HenrySays 139 1)
					;"Oh.  'ello!    Go on in."
					(ego setScript: (ScriptID 82 2))
					(client setScript: 0)
				else
					(TimePrint 4 139 2 #title {the Hermit mutters...})
					;"Mpfph..  grumble..  nobody 'ere, I guess."
					(= cycles 1)
				)
			)
			(6
				(++ hermitHeadMove)
				((ScriptID 82 1) setCel: 0)
				(= cycles 3)
			)
			(7
				((ScriptID 82 1) setCel: 1)
				(= cycles 3)
			)
			(8
				(if (< hermitHeadMove 4)
					(self changeState: 6)
				else
					(self cue:)
				)
			)
			(9
				(if (Btst fEgoSquashed)
					(ego
						view: vEgoFallDown
						setLoop: 0
						cel: 0
						x: (- (ego x?) 5)
						y: (- (ego y?) 3)
						stopUpd:
						show:
					)
				)
				((ScriptID 82 1) dispose:)
				(client setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(10
				(Bclr fHenryDoorOpen)
				(if (Btst fEgoSquashed)
					(ego setScript: (ScriptID 82 3))
				)
				(client setScript: 0)
			)
		)
	)
)
