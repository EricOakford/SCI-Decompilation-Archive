;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPINV) ;15
(include game.sh) (include "15.shm")
(use Main)
(use BordWind)
(use IconBar)
(use Motion)
(use Invent)
(use System)

(public
	invCode 0
	invWin 1
)

(local
	oldSpeed
)
(class FPIconItem of IconItem
	
	(method (ownedBy)
		(return FALSE)
	)
)

(class FPInvItem of InvItem
	(properties
		modNum 0
	)
	
	(method (init)
		(= lowlightColor 19)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(narrator name: name showTitle: TRUE)
		(cond 
			((== theVerb V_LOOK)
				(messager say: noun V_LOOK NULL 0 0 FPINV)
			)
			((not (OneOf theVerb V_WALK V_DO V_TALK 5 V_HELP V_EXIT))
				(messager say: NULL V_USEIT NULL 0 0 FPINV)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance invCode of Code
	(properties)
	
	(method (init)
		(= inventory Inventory)
		(Inventory
			init:
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			add:
				Empty_Vial
				Full_Vial
				Beer_Bottle
				Empty_Bottle
				Full_Bottle
				Charcoal
				Saltpeter
				Full_Saltpeter
				Tin_Can
				Empty_Cup
				Full_Cup
				Mole
				Prescription
				Fuse
				Matches
				Unlit_Bomb
				Bomb
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor 37
			eachElementDo: #lowlightColor 33
			eachElementDo: #modNum 15
			eachElementDo: #init
			state: NOCLICKHELP
		)
	)
)

(instance invWin of InsetWindow
	(properties
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open &tmp i node obj)
		(= i 0)
		(= node (inventory first:))
		(while node
			(if
				(not
					((= obj (NodeValue node)) isKindOf: InvItem)
				)
				(= i
					(+
						i
						(CelWide (obj view?) (obj loop?) (obj cel?))
					)
				)
			)
			(= node (inventory next: node))
		)
		(super open:)
		(invLook nsLeft: (/ (- (- right left) i) 2))
	)
)

(instance ok of FPIconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		helpVerb V_HELP
	)
)

(instance invLook of FPIconItem
	(properties
		view 991
		loop 2
		cel 0
		cursor 1
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		helpVerb V_HELP
	)
)

(instance invHand of FPIconItem
	(properties
		view 991
		loop 0
		cel 0
		cursor 2
		message V_DO
		noun N_HAND
		helpVerb V_HELP
	)
)

(instance invHelp of FPIconItem
	(properties
		view 991
		loop 1
		cel 0
		cursor 9
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance invSelect of FPIconItem
	(properties
		view 991
		loop 4
		cel 0
		cursor ARROW_CURSOR
		noun N_SELECT
		helpVerb V_HELP
	)
)

(instance Empty_Vial of FPInvItem
	(properties
		view 50
		loop 1
		cursor 50
		message V_VIAL_EMPTY
		signal IMMEDIATE
		noun N_EMPTY_VIAL
		name "Empty Vial"
	)
)

(instance Full_Vial of FPInvItem
	(properties
		view 51
		loop 1
		cursor 51
		message V_VIAL_FULL
		signal IMMEDIATE
		noun N_FULL_VIAL
		name "Full Vial"
	)
)

(instance Beer_Bottle of FPInvItem
	(properties
		view 63
		loop 1
		cursor 63
		message V_BEER
		signal IMMEDIATE
		noun N_BEER_BOTTLE
		name "Beer Bottle"
	)
)

(instance Empty_Bottle of FPInvItem
	(properties
		view 53
		loop 1
		cursor 53
		message V_BOTTLE_EMPTY
		signal IMMEDIATE
		noun N_EMPTY_BOTTLE
		name "Empty Bottle"
	)
	
	(method (doVerb theVerb)
		(narrator name: name showTitle: TRUE)
		(switch theVerb
			(V_CUP_FULL
				(inventory curIcon: 0 hide:)
				(ego setScript: sPourCoffee)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Full_Bottle of FPInvItem
	(properties
		view 52
		loop 1
		cursor 52
		message V_BOTTLE_FULL
		signal IMMEDIATE
		noun N_FULL_BOTTLE
		name "Full Bottle"
	)
)

(instance Charcoal of FPInvItem
	(properties
		view 54
		loop 1
		cursor 54
		message V_CHARCOAL
		signal IMMEDIATE
		noun N_CHARCOAL
	)
)

(instance Saltpeter of FPInvItem
	(properties
		view 55
		loop 1
		cursor 55
		message V_SALTPETER_EMPTY
		signal IMMEDIATE
		noun N_EMPTY_SALTPETER
	)
)

(instance Full_Saltpeter of FPInvItem
	(properties
		view 55
		loop 1
		cursor 55
		message V_SALTPETER_FULL
		signal IMMEDIATE
		noun N_FULL_SALTPETER
		name "Full Saltpeter"
	)
)

(instance Tin_Can of FPInvItem
	(properties
		view 56
		loop 1
		cursor 56
		message V_TINCAN
		signal IMMEDIATE
		noun N_TIN_CAN
		name "Tin Can"
	)
	
	(method (doVerb theVerb &tmp theCase)
		(narrator name: name showTitle: TRUE)
		(switch theVerb
			(V_LOOK
				(cond 
					((and (Btst fPutCharcoalInCan) (Btst fPutSulfurInCan) (Btst fPutSaltpeterInCan))
						(= theCase C_ALL)
					)
					((and (Btst fPutCharcoalInCan) (Btst fPutSaltpeterInCan))
						(= theCase C_SALTPETER_CHARCOAL)
					)
					((and (Btst fPutSaltpeterInCan) (Btst fPutSulfurInCan))
						(= theCase C_SALTPETER_SULFUR)
					)
					((and (Btst fPutCharcoalInCan) (Btst fPutSulfurInCan))
						(= theCase C_SULFUR_CHARCOAL)
					)
					((Btst fPutCharcoalInCan)
						(= theCase C_CHARCOAL)
					)
					((Btst fPutSaltpeterInCan)
						(= theCase C_SALTPETER)
					)
					((Btst fPutSulfurInCan)
						(= theCase C_SULFUR)
					)
					(else
						(= theCase NULL)
					)
				)
				(messager say: noun theVerb theCase 0 0 FPINV)
			)
			(V_CHARCOAL
				(inventory hide: curIcon: 0)
				(messager say: noun theVerb NULL 0 0 FPINV)
				(Bset fPutCharcoalInCan)
				(++ ingredientsInCan)
				(ego put: iCharcoal)
			)
			(V_SALTPETER_FULL
				(inventory hide: curIcon: 0)
				(ego put: iFullSaltpeter)
				(Bset fPutSaltpeterInCan)
				(++ ingredientsInCan)
				(messager say: noun theVerb NULL 0 0 FPINV)
			)
			(V_SALTPETER_EMPTY
				(messager say: noun theVerb NULL 0 0 FPINV)
			)
			(V_VIAL_FULL
				(inventory hide: curIcon: 0)
				(ego put: iFullVial)
				(Bset fPutSulfurInCan)
				(++ ingredientsInCan)
				(messager say: noun theVerb NULL 0 0 FPINV)
			)
			(V_FUSE
				(if (== ingredientsInCan 3)
					(inventory hide: curIcon: 0)
					(ego put: iFuse)
					(ego put: iTinCan)
					(ego get: -1 iUnlitBomb)
					(++ ingredientsInCan)
					(= theCase C_ATTACHFUSE)
				else
					(= theCase C_CAN_EMPTY)
				)
				(messager say: noun theVerb theCase 0 0 FPINV)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Empty_Cup of FPInvItem
	(properties
		view 57
		loop 1
		cursor 57
		message V_CUP_EMPTY
		signal IMMEDIATE
		noun N_EMPTY_MUG
		name "Empty Cup"
	)
)

(instance Full_Cup of FPInvItem
	(properties
		view 58
		loop 1
		cursor 58
		message V_CUP_FULL
		signal IMMEDIATE
		noun N_COFFEE
		name "Full Cup"
	)
)

(instance Mole of FPInvItem
	(properties
		view 59
		loop 1
		cursor 59
		message V_MOLE
		signal IMMEDIATE
		noun N_MOLE
	)
)

(instance Prescription of FPInvItem
	(properties
		view 60
		loop 1
		cursor 60
		message V_PRESCRIPTION
		signal IMMEDIATE
		noun N_PRESCRIPTION
	)
)

(instance Fuse of FPInvItem
	(properties
		view 61
		loop 1
		cursor 61
		message V_FUSE
		signal IMMEDIATE
		noun N_FUSE
	)
	
	(method (doVerb theVerb)
		(narrator name: name showTitle: TRUE)
		(switch theVerb
			(V_MATCHES
				(messager say: noun theVerb NULL 0 0 FPINV)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Matches of FPInvItem
	(properties
		view 62
		loop 1
		cursor 62
		message V_MATCHES
		signal IMMEDIATE
		noun N_MATCHES
	)
)

(instance Unlit_Bomb of FPInvItem
	(properties
		view 65
		loop 1
		cursor 65
		message V_BOMB_UNLIT
		signal IMMEDIATE
		noun N_UNLIT_BOMB
		name "Unlit Bomb"
	)
	
	(method (doVerb theVerb)
		(narrator name: name showTitle: TRUE)
		(switch theVerb
			(V_MATCHES
				(curRoom setScript: sGetBomb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Bomb of FPInvItem
	(properties
		view 64
		loop 1
		cursor 64
		message V_BOMB_LIT
		signal IMMEDIATE
		noun N_BOMB
	)
)

(instance sGetBomb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(inventory curIcon: Bomb hide:)
				(ego put: iUnlitBomb)
				(ego get: -1 iBomb)
				(soundFx number: 739 loop: 1 play: self)
			)
			(1
				(soundFx number: 740 loop: -1 play:)
				(messager say: N_UNLIT_BOMB V_MATCHES NULL 0 0 FPINV)
				(self dispose:)
			)
		)
	)
)

(instance sPourCoffee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= oldSpeed (ego cycleSpeed?))
				(ego setMotion: 0)
				(ego put: iEmptyBottle)
				(ego put: iFullCup)
				(ego get: -1 iFullBottle)
				(ego view: 674 cel: 0 setSpeed: 12 setCycle: EndLoop self)
				(soundFx number: 813 loop: 1 play:)
			)
			(1
				(messager say: N_EMPTY_BOTTLE 12 NULL 0 self FPINV)
				(ego normalize: setSpeed: oldSpeed)
				(soundFx stop:)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
