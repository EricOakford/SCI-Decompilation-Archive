;;; Sierra Script 1.0 - (do not remove this comment)
(script# 407)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	MMSaids 0
)

(local
	local0 = [
		407 0
		407 1
		407 2
		407 3
		407 4
		407 5
		407 6
		407 7
		407 8
		407 9
		407 10
		407 11
		407 12
		407 13
		407 14
		407 15
		407 16
		407 17
		407 18
		407 18
		407 18
		407 19
		407 20
		407 21
		407 22
		407 23
		407 24
		407 25
		407 26
		407 27
		407 28
		407 28
		407 28
		407 29
		407 29
		407 29
		407 30
		407 31
		407 32
		407 33
		407 34
		407 35
		407 36
		407 37
		407 38
		407 22
		407 23
		407 24
		407 39
		407 39
		407 39
		407 40
		407 41
		407 42
		407 18
		407 18
		407 18
		407 43
		407 44
		407 45
		407 46
		407 47
		407 48
		407 49
		407 50
		407 51
		407 52
		407 53
		407 54
		407 55
		407 56
		407 57
		]
	local144
)
(procedure (localproc_000c &tmp i temp1)
	(if theInvItem
		(= i
			(+
				(= i
					(+
						(= i (* (- theTalker 1) 96))
						(* (- global212 1) 24)
					)
				)
				whichItem
			)
		)
		(= temp1 (<< $0001 (mod i 16)))
		(if (& [global302 (= i (/ i 16))] temp1)
			(return 0)
		)
		(= [global302 i] (| [global302 i] temp1))
	)
	(return TRUE)
)

(instance MMSaids of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 407)
	)
	
	(method (handleEvent event &tmp i [temp1 2])
		(= local144 (<< $0001 (- theTalker 1)))
		(if (and (ego has: iPouch) (Said '/diamond>'))
			(cond 
				((Said 'examine')
					(Print 407 58)
				)
				((Said 'get,wear')
					(Print 407 59)
				)
			)
		)
		(if (event claimed?)
			(client setScript: 0)
			(return)
		)
		(= i -1)
		(cond 
			((Said 'blow/nose')
				(if (ego has: iHandkerchief)
					(Ok)
					(getItemSound number: 116 loop: 1 priority: 10 play:)
					(getItemSound number: 93 priority: 2 owner: -1)
				else
					(Print 407 60)
				)
			)
			((Said 'examine/fingerprint')
				(if (ego has: iMonocle)
					(cond 
						((and (ego has: iDiary) (ego has: iBrokenRecord))
							(Print 407 61)
						)
						((ego has: iDiary)
							(Print 407 62
								#icon 637 0 0
							)
							(Bset fExaminedDiary)
						)
						((ego has: iBrokenRecord)
							(Print 407 63
								#icon 636 0 0
							)
							(Bset fExaminedRecord)
						)
					)
				else
					(Print 407 64)
				)
			)
			((Said 'examine/laura')
				(= theTalker talkLAURA)
				(Say 0 407 65)
			)
			((Said 'examine/bootprint')
				(Print 407 66)
			)
			((Said 'converse>')
				(Print 407 67)
				(event claimed: TRUE)
			)
			((Said 'shoot')
				(cond 
					(gunIsLoaded
						(Print 407 68)
					)
					((ego has: iDerringer)
						(Print 407 69)
					)
					(else
						(Print 407 70)
					)
				)
			)
			((Said 'unbar/*')
				(if (or (ego has: iSkeletonKey) (ego has: iBrassKey))
					(Print 407 71)
				else
					(Print 407 72)
				)
			)
			((and (ego has: iLantern) (Said 'get/kerosene'))
				(Print 407 73)
			)
			((Said 'eat')
				(Print 407 74)
			)
			((Said 'hop')
				(Print 407 75)
			)
			((Said 'kill')
				(Print 407 76)
			)
			((Said 'rob')
				(Print 407 77)
			)
			((Said 'hit')
				(Print 407 78)
			)
			((Said 'climb')
				(Print 407 79)
			)
			((Said 'close/door')
				(Print 407 80)
			)
			((Said 'hear')
				(Print 407 81)
			)
			((Said 'converse')
				(Print 407 82)
			)
			((Said 'bye')
				(Print 407 83)
			)
			((Said 'sit')
				(Print 407 84)
			)
			((Said 'smell')
				(Print 407 85)
			)
			((Said 'get>')
				(cond 
					((Said '/water')
						(Print 407 86)
					)
					((Said '[/!*]')
						(Print 407 87)
					)
					(else
						(Print 407 88)
					)
				)
				(event claimed: TRUE)
			)
			((or (Said 'ask/*<for') (Said 'ask//*<for'))
				(Print 407 89)
			)
			((Said 'ask>')
				(if (Said '//*<about>')
					(if (and (& local144 global210) (!= local144 global211))
						(if (localproc_000c)
							(= i (* (Random 0 5) 3 2))
						else
							(= i 120)
						)
					else
						(Print 407 67)
						(event claimed: TRUE)
					)
				else
					(Print 407 90)
				)
				(event claimed: TRUE)
			)
			((Said 'tell>')
				(if (Said '//*<about>')
					(if (and (& local144 global210) (!= local144 global211))
						(if (localproc_000c)
							(= i (* (+ (* (Random 0 5) 3) 18) 2))
						else
							(= i 126)
						)
					else
						(Print 407 67)
						(event claimed: TRUE)
					)
				else
					(Print 407 91)
				)
				(event claimed: TRUE)
			)
			((Said 'open,(examine<in)>')
				(cond 
					((Said '[/!*]')
						(Print 407 92)
					)
					((Said '/door')
						(Print 407 93)
					)
					(else
						(CantDo)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'examine>')
				(if (Said '/person,fellow,girl')
					(Print 407 94)
				else
					(SeeNothing)
				)
				(event claimed: TRUE)
			)
			((Said 'deliver>')
				(cond 
					(haveInvItem
						(if (& local144 global210)
							(if (localproc_000c)
								(= i (* (+ (* (Random 0 3) 3) 36) 2))
							else
								(= i 132)
							)
						else
							(Print 407 95)
						)
					)
					((Said '[/!*]')
						(Print 407 96)
					)
					(else
						(Print 407 97)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'hold>')
				(cond 
					(haveInvItem
						(if (& local144 global210)
							(if (localproc_000c)
								(= i (* (+ (* (Random 0 3) 3) 48) 2))
							else
								(= i 138)
							)
						else
							(Print 407 95)
						)
					)
					((Said '[/!*]')
						(Print 407 98)
					)
					(else
						(DontHave)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'drop,throw,use')
				(DontHave)
			)
			((Said 'who/game<program')
				(Print 407 99)
			)
			((Said 'draw<who/game')
				(Print 407 100)
			)
			((Said 'write<who/music')
				(Print 407 101)
			)
			((Said 'is<who/lord<stump')
				(Print 407 102)
			)
			((Said 'move,press,drag')
				(CantDo)
			)
		)
		(if (!= i -1)
			(Say
				1
				[local0 i]
				[local0 (+
					(switch theTalker
						(2 (+= i 2))
						(5 (+= i 4))
					)
					1
				)]
			)
			(event claimed: TRUE)
		)
		(client setScript: 0)
	)
)
