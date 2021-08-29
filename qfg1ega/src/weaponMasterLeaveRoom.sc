;;; Sierra Script 1.0 - (do not remove this comment)
(script# 224)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	leaveRoom 0
)

(instance leaveRoom of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 224)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fStopFightingMaster)
				((ScriptID 221 0)
					view: vWeaponMasterTalk
					setLoop: (if ((ScriptID 221 0) fightLeft?) 5 else 4)
					cel: 0
				)
				(= cycles 2)
			)
			(1
				(HighPrint 224 0)
				;"Even as we fought, I could detect your skills improving through practice."
				(if ((client opponent?) endFight?)
					(HighPrint 224 1)
					;"It was most prudent that you should give in to my superior skill."
				else
					(HighPrint 224 2)
					;"Still, you seem to tire more easily than you should."
				)
				(switch (Random 0 4)
					(0
						(HighPrint 224 3)
						;"Be more aware of the angle with which your weapon approaches your opponent.
						;The thrust bites quick and deep, but the swing will do more damage if it connects."
						)
					(1
						(HighPrint 224 4)
						;"The art of dancing would be a good skill for you to practice, my friend.
						;The balance of the body over the rapidly moving feet has won many a contest."
						)
					(2
						(HighPrint 224 5)
						;"You have a tendency to overbalance when you dodge a blow.
						;This will adversely affect the timing and effectiveness of your counterattack."
						)
					(3
						(HighPrint 224 6)
						;"Put your attention to the control of your legs when you duck a swing.
						;Your springiness makes you seem to bounce, and a good opponent (like myself) will always take advantage of the opening."
						)
					(4
						(HighPrint 224 7)
						;"If you would like a stretcher, my weak, tired friend...
						;You seem a shadow of the person who began this little lesson."
						)
				)
				(HighPrint 224 8)
				;"Should we meet again, I would not be adverse to another go-round.  Farewell, friend!"
				((ScriptID 221 0)
					setLoop: (if (== ((ScriptID 221 0) loop?) 4) 1 else 0)
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion:
						MoveTo
						(if ((ScriptID 221 0) fightLeft?) 340 else -20)
						((ScriptID 221 0) y?)
						self
				)
				(Bclr fMasterIsHere)
				(Bclr fStopFightingMaster)
			)
			(2
				(HandsOn)
				(client dispose:)
			)
		)
	)
)
