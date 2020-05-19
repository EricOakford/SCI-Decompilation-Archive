;;; Sierra Script 1.0 - (do not remove this comment)
(script# MENACE)
(include game.sh)
(use Main)
(use LoadMany)
(use Game)
(use System)

(public
	rgMenace 0
	startScript 1
	waitAFew 2
)

(local
	local0
	local1
)
(instance rgMenace of Region
	(properties)
	
	(method (init)
		(self keep: 0)
		(super init:)
		(LoadMany SCRIPT AVOIDER SIGHT CHASE)
		(switch prevRoomNum
			((curRoom north?) 0)
			((curRoom south?) 0)
			((curRoom west?) 0)
			((curRoom east?) 0)
			(else  (= local1 1))
		)
		(= menaceInRoom FALSE)
		(= global139 0)
		(switch
			(cond 
				(local1
				(switch curRoomNum
					(29 0)
					(46 2)
					(35 1)
				))
				((ego has: iMagicShield) (Random 0 1))
				(else (Random 0 2))
			)
			(0
				(LoadMany SOUND 34 26 90)
				(LoadMany VIEW 100 101 28 102)
				(Load SCRIPT SORCERER)
				(= menaceWaiting SORCERER)
				(if
					(not
						(if (and (ego has: iMagicMirror) (ego has: iMagicShield)) (ego has: iChest))
					)
					(switch (Random 0 3)
						(1
							(Load VIEW 135)
							(Load SOUND 93)
							(Load SCRIPT DWARF)
							(= menaceInRoom DWARF)
						)
						(3
							(LoadMany VIEW 130 20)
							(Load SOUND 35)
							(Load SCRIPT OGRE)
							(= menaceInRoom OGRE)
						)
						(else
							(= menaceInRoom 0)
						)
					)
				)
			)
			(1
				(Load VIEW 135)
				(Load SOUND 93)
				(Load SCRIPT DWARF)
				(= menaceWaiting DWARF)
			)
			(2
				(LoadMany VIEW 130 20)
				(Load SOUND 35)
				(Load SCRIPT OGRE)
				(= menaceWaiting OGRE)
			)
		)
		(theMenace
			illegalBits: -16384
			setLoop: -1
			setCel: -1
			setPri: -1
			cycleSpeed: 0
			moveSpeed: 0
		)
		(self setScript: waitOne)
	)
	
	(method (dispose)
		(DisposeScript menaceWaiting)
		(if menaceInRoom (DisposeScript menaceInRoom))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
)

(instance waitOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(if
					(and
						(ego inRect: 20 (+ (curRoom horizon?) 12) 300 176)
						(not (Btst fInvisible))
						(not haloTimer)
						(not (curRoom script?))
						(not (ego script?))
						(== newRoomNum curRoomNum)
					)
					(curRoom setScript: startScript)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance startScript of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID menaceWaiting 0) self)
			)
			(1
				(if menaceInRoom 0 else (self dispose:))
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance waitAFew of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(self setScript: (ScriptID menaceInRoom 0) self)
			)
			(2
				(startScript cue:)
				((ScriptID menaceWaiting 0) cue:)
				(self dispose:)
			)
		)
	)
)
