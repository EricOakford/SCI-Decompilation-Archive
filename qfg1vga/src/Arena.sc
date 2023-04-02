;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA)
(include game.sh)
(use Main)
(use StatusBar)
(use Skilled)
(use Monster)
(use Game)
(use Actor)


(class Arena of Room
	(properties
		monster 0
		inTransit FALSE
		escaped FALSE
	)
	
	(method (init)
		(addToPics add:)
		(Load SCRIPT WARRIOR)
		(Load SCRIPT vMinotaur)
		Monster
		StatusBar
		SkilledActor
		(Load SCRIPT CLOSECOMBAT)
		(Load SCRIPT PAINREACTION)
		(super init: &rest)
		(Bclr fMonsterRecoils)
		(Bclr fSaveAllowed)	;can't save in battle
		(statusBackEgo init:)
		(statusBackMon init:)
		(addToPics add: statusBackEgo doit:)
		(addToPics add: statusBackMon doit:)
		((ScriptID WARRIOR 0)
			view: vSwFight
			init:
			stopUpd:
			opponent: monster
			drawStatus:
			startCombat: CLOSECOMBAT
		)
	)
	
	(method (doit)
		(cond 
			(inTransit
				(super doit:)
			)
			(escaped
				(= monsterHealth (monster health?))
				(= inTransit TRUE)
				(curRoom newRoom: prevRoomNum)
			)
			(
				(and
					(<= (monster health?) 0)
					(IsObject (ScriptID WARRIOR 0))
					((ScriptID WARRIOR 0) script?)
					(not (((ScriptID WARRIOR 0) script?) script?))
					(!= monsterNum vBrigand)
					(!= monsterNum vGoblin)
					(!= monsterNum vBear)
				)
				(= inTransit TRUE)
				(= monsterHealth 0)
				(Animate (cast elements?) FALSE)
				(curRoom newRoom: prevRoomNum)
			)
			(else
				(super doit:)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript vMinotaur)
		(DisposeScript SKILLED)
		(DisposeScript PAINREACTION)
		(DisposeScript WARRIOR)
		(DisposeScript MONSTER)
		(DisposeScript STATUSBAR)
		(DisposeScript CLOSECOMBAT)
		(Bset fSaveAllowed)
		(user canInput: TRUE)
		(statusBackEgo dispose:)
		(statusBackMon dispose:)
		(super dispose: &rest)
		(DisposeScript ARENA)
	)
)

(instance statusBackEgo of View
	(properties
		x 9
		y 7
		view vStatusBar
		cel 1
	)
)

(instance statusBackMon of View
	(properties
		x 232
		y 6
		view vStatusBar
	)
)
