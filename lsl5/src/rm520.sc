;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include game.sh)
(use Main)
(use LLRoom)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm520 0
)

(local
	saveBits
	saveBits2
)
(procedure (RestoreUnderbits)
	(Display 520 13 p_restore saveBits)
	(Display 520 13 p_restore saveBits2)
)

(instance rm520 of LLRoom
	(properties
		lookStr {You are looking at "The Hard Disk Cafe's" exclusive paper membership tape reader. It controls admittance to the club.}
		picture 520
	)
	
	(method (init)
		(Load SOUND 258)
		(HandsOff)
		(InFirstPerson 1)
		(super init:)
		(ego hide:)
		(switch prevRoomNum
			(510
				(self setScript: sLobby)
			)
			(else
				(self setScript: sCafe)
			)
		)
	)
	
	(method (doVerb)
		(self setScript: sTapeout 0 prevRoomNum)
	)
	
	(method (newRoom n)
		(InFirstPerson 0)
		(super newRoom: n)
	)
)

(instance sLobby of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (not (ego has: iMembershipTape)) (+= state 2))
				(= cycles 2)
			)
			(1
				(TimePrint 520 0)
				(tape init: setCycle: EndLoop setMotion: MoveTo 313 18 self)
				(sucking play:)
			)
			(2 (= seconds 2))
			(3
				(if (not (ego has: iMembershipTape))
					(Display 520 1
						p_at 75 60
						p_color HDCdisplayColor
						p_width 150
						p_font smallFont
					)
					(= seconds 5)
				else
					(= saveBits
						(Display 520 2
							p_at 75 60
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
							p_save
						)
					)
					(++ state)
					(= seconds 3)
				)
			)
			(4
				(TimePrint 520 3 #at -1 185)
				(curRoom newRoom: 510)
			)
			(5
				(HandsOn)
				(switch ((Inventory at: iMembershipTape) state?)
					(HDCgotTape
						(Display 520 4
							p_at 75 72
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
						)
					)
					(HDCbribed
						(Display 520 5
							p_at 75 72
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
						)
					)
					(HDChacked
						(Display 520 6
							p_at 75 72
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
						)
					)
				)
				(= seconds 5)
			)
			(6
				(curRoom setScript: sTapeout 0 510)
			)
		)
	)
)

(instance sCafe of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(TimePrint 520 0)
				(tape init: setCycle: EndLoop setMotion: MoveTo 313 18 self)
				(sucking play:)
			)
			(2 (= seconds 2))
			(3
				(= saveBits
					(Display 520 2
						p_at 75 60
						p_color HDCdisplayColor
						p_width 150
						p_font smallFont
						p_save
					)
				)
				(= seconds 3)
			)
			(4
				(switch ((Inventory at: iMembershipTape) state?)
					(HDCgotTape
						(= saveBits2
							(Display 520 7
								p_at 75 72
								p_color HDCdisplayColor
								p_width 150
								p_font smallFont
								p_save
							)
						)
						(= register 1)
					)
					(HDCbribed
						(HandsOn)
						(SolvePuzzle 8 fInsertedMembershipTape)
						(Display 520 8
							p_at 75 72
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
						)
					)
					(HDChacked
						(HandsOn)
						(SolvePuzzle 12 fInsertedMembershipTape)
						(Display 520 9
							p_at 75 72
							p_color HDCdisplayColor
							p_width 150
							p_font smallFont
						)
					)
				)
				(= seconds 5)
			)
			(5
				(if register
					(RestoreUnderbits)
					(Display 520 10
						p_at 75 72
						p_color HDCdisplayColor
						p_width 150
						p_font smallFont
					)
					(HandsOn)
					(= seconds 6)
				else
					(= cycles 1)
				)
			)
			(6
				(curRoom setScript: sTapeout 0 525)
			)
		)
	)
)

(instance sTapeout of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (> ((Inventory at: iMembershipTape) state?) HDCgotTape) (== prevRoomNum 525))
					(TimePrint 520 11)
					(TimePrint 520 12)
					((Inventory at: iMembershipTape) state: HDCdone)
					(ego put: iMembershipTape 0)
					(= cycles 1)
				else
					(sucking play:)
					(tape setCycle: BegLoop setMotion: MoveTo 302 55 self)
				)
			)
			(1 (curRoom newRoom: register))
		)
	)
)

(instance tape of Actor
	(properties
		x 302
		y 55
		description {your tape}
		sightAngle 90
		lookStr {Your membership tape hangs from the machine's open lip.}
		view 520
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
		cycleSpeed 20
	)
)

(instance sucking of Sound
	(properties
		number 258
	)
)
