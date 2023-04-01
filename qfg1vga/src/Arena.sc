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
		inTransit 0
		escaped 0
	)
	
	(method (init)
		(addToPics add:)
		(Load SCRIPT 213)
		(Load SCRIPT 425)
		Monster
		StatusBar
		SkilledActor
		(Load SCRIPT 215)
		(Load SCRIPT 155)
		(super init: &rest)
		(Bclr fMonsterRecoils)
		(Bclr fSaveAllowed)	;can't save in battle
		(statusBackEgo init:)
		(statusBackMon init:)
		(addToPics add: statusBackEgo doit:)
		(addToPics add: statusBackMon doit:)
		((ScriptID 213 0)
			view: 109
			init:
			stopUpd:
			opponent: monster
			drawStatus:
			startCombat: 215
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
					(IsObject (ScriptID 213 0))
					((ScriptID 213 0) script?)
					(not (((ScriptID 213 0) script?) script?))
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
		(DisposeScript 212)
		(DisposeScript 155)
		(DisposeScript 213)
		(DisposeScript 214)
		(DisposeScript 205)
		(DisposeScript 215)
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
