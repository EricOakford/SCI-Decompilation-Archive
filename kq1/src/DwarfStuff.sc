;;; Sierra Script 1.0 - (do not remove this comment)
(script# DWARF)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Timer)
(use Avoider)
(use Motion)
(use System)

(public
	DwarfStuff 0
)

(local
	dwarfConfused
)
(procedure (DwarfSteals what points)
	(return
		(if (ego has: what)
			(ego put: what)
			(theGame changeScore: (- 0 score))
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(instance DwarfStuff of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(Print 804 0)
	)
	
	(method (doit)
		(super doit:)
		(if (and (Btst fInvisible) (not dwarfConfused))
			(= dwarfConfused TRUE)
			(Print 804 5)
			(self changeState: 2)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= ((ScriptID 0 21) signal?) -1)
					((ScriptID 0 21) loop: 1 fade:)
				)
				((ScriptID 0 23) number: 93 loop: -1 play:)
				(switch prevRoomNum
					((curRoom east?)
						(theMenace posn: 319 141)
					)
					((curRoom west?)
						(theMenace posn: 1 130)
					)
					(else 
						(if (Random 0 1)
							(theMenace posn: 319 141)
						else
							(theMenace posn: 1 130)
						)
					)
				)
				(theMenace
					view: 135
					setCycle: Walk
					ignoreActors:
					ignoreHorizon:
					illegalBits: cWHITE
					init:
					setStep: 4 3
					cycleSpeed: 0
					setAvoider: Avoider 1
					setMotion: Chase ego 16 self
				)
			)
			(1
				(if (Btst fClimbingHill)
					(theMenace setMotion: 0)
					(self dispose:)
				else
					(theMenace setMotion: 0)
					(Print 804 6)
					(cond 
						((DwarfSteals iMagicShield 8))
						((DwarfSteals iMagicMirror 8))
						((DwarfSteals iChest 8))
						((and (Btst fOpenedPouch) (DwarfSteals iPouch 6)) 0)
						((DwarfSteals iPouch 3) 0)
						((and (Btst fOpenedWalnut) (DwarfSteals iWalnut 6)) 0)
						((DwarfSteals iGoldEgg 6) 0)
						((DwarfSteals iSceptre 6) 0)
					)
					(Timer setCycle: self 2)
				)
			)
			(2
				(if (< (ego heading?) 180)
					(theMenace setMotion: MoveTo -20 (theMenace y?) self)
				else
					(theMenace setMotion: MoveTo 330 (theMenace y?) self)
				)
			)
			(3
				((ScriptID 0 23) stop:)
				(if menaceInRoom 0 else (theMenace dispose:))
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((or (Said 'look,check/dwarf,man[<little]') (MousedOn theMenace event shiftDown))
				(event claimed: TRUE)
				(if (< state 2)
					(Print 804 1)
				else
					(Print 804 2)
				)
			)
			((Said 'talk,speak[/dwarf]')
				(Print 804 3)
			)
			((Said 'kill,capture,attack,shoot/dwarf,man[<little]')
				(Print 804 4)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)
