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
		(Bclr 285)
		(Bclr 129)
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
			(inTransit (super doit:))
			(escaped
				(= monsterHealth (monster health?))
				(= inTransit 1)
				(curRoom newRoom: prevRoomNum)
			)
			(
				(and
					(<= (monster health?) 0)
					(IsObject (ScriptID 213 0))
					((ScriptID 213 0) script?)
					(not (((ScriptID 213 0) script?) script?))
					(!= monsterNum 465)
					(!= monsterNum 445)
					(!= monsterNum 420)
				)
				(= inTransit 1)
				(= monsterHealth 0)
				(Animate (cast elements?) 0)
				(curRoom newRoom: prevRoomNum)
			)
			(else (super doit:))
		)
	)
	
	(method (dispose)
		(DisposeScript 425)
		(DisposeScript 212)
		(DisposeScript 155)
		(DisposeScript 213)
		(DisposeScript 214)
		(DisposeScript 205)
		(DisposeScript 215)
		(Bset 129)
		(user canInput: 1)
		(statusBackEgo dispose:)
		(statusBackMon dispose:)
		(super dispose: &rest)
		(DisposeScript 211)
	)
)

(instance statusBackEgo of View
	(properties
		x 9
		y 7
		view 803
		cel 1
	)
)

(instance statusBackMon of View
	(properties
		x 232
		y 6
		view 803
	)
)
