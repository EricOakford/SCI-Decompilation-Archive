;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA)	;211
(include game.sh)
(use Main)
(use LoadMany)
(use Game)


(class Arena of Room
	(properties
		style IRISIN
		monster 0
		inTransit 0
		escaped 0
	)
	
	(method (init)
		(Load SCRIPT WARRIOR)
		(Load SCRIPT STATUSBAR)
		(LoadMany VIEW statusBarView vEgoFightArmSword vEgoFightArmDagger)
		(super init:)
		(StatusLine enable:)
		(Bclr fSaveAllowed) ;can't save in combat
		(= hadABattle TRUE)
		((ScriptID WARRIOR 0)
			init:
			stopUpd:
			weaponView: (if (ego has: iSword) vEgoFightArmSword else vEgoFightArmDagger)
			opponent: monster
			drawStatus:
			startCombat: CLOSECOMBAT
		)
		(monster init: drawStatus:)
	)
	
	(method (doit)
		(cond 
			(inTransit
				(super doit:)
			)
			(escaped
				(= monsterHealth (monster health?))
				(= inTransit TRUE)
				(monster dispose:)
				((ScriptID WARRIOR 0) dispose:)
				(curRoom newRoom: prevRoomNum)
			)
			((<= (monster health?) 0)
				(= brigandHead 0)
				(= monsterHealth 0)
				(= inTransit TRUE)
				(Animate (cast elements?) FALSE)
				(monster dispose:)
				((ScriptID WARRIOR 0) dispose:)
				(curRoom newRoom: prevRoomNum)
			)
			(else
				(super doit:)
			)
		)
	)
	
	(method (dispose)
		(Bset fSaveAllowed)	;allow saving again
		(super dispose:)
	)
)
