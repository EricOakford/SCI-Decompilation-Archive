;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh) (include "15.shm")
(use Main)
(use GKIconItem)
(use Procs)
(use Inset)
(use IconBar)
(use Actor)
(use System)

(public
	insetCode 0
)

(local
	local0
	newEventHandler
	local2
	theIcon
	[local4 14]
)
(instance insetCode of Code

	(method (doit theView theLoop theCel theX theY theNoun param7 &tmp i)
		(for ((= i 0)) (< i 14) ((++ i))
			(= [local4 i] 0)
		)
		(theIconBar eachElementDo: #perform checkIcon)
		(theIconBar disable:
			ICON_WALK
			ICON_TAKE
			ICON_OPEN	
			ICON_INVENTORY
			ICON_HELP
			ICON_OPERATE
			ICON_MOVE
			ICON_ASK
			ICON_TALK
			ICON_RECORDER
			ICON_USEITEM
		)
		(= local0 0)
		(= newEventHandler 0)
		(= local2 0)
		(if (and (== argc 7) param7)
			(= local0 1)
			(magFrame noun: theNoun)
		)
		(invInset
			view: theView
			loop: theLoop
			cel: theCel
			x: theX
			y: theY
			noun: theNoun
			priority: 15
			disposeNotOnMe: 1
		)
		(if (curRoom inset:)
			(= local2 ((curRoom inset:) caller?))
			((curRoom inset:) caller: 0 dispose:)
		)
		(invInset init: 0 curRoom)
	)
)

(instance magFrame of View
	(properties
		x 204
		y 117
		modNum GK_INV
		view 1
	)
	
	(method (doVerb theVerb)
		(if msgType
			(cond 
				((Message MsgGet modNum noun theVerb NULL 1)
					(messager say: noun theVerb NULL 0 0 modNum)
				)
				((== theVerb V_MAG_GLASS)
					(messager say: NULL V_MAG_GLASS C_ALREADY_USING_GLASS 0 0 GK_INV)
				)
				(else
					(messager say: NULL theVerb NULL 0 0 0)
				)
			)
		)
		(return TRUE)
	)
)

(instance invInset of Inset
	(properties
		modNum GK_INV
	)
	
	(method (init &tmp i)
		(= theIcon (GKIconbar curIcon?))
		(GKIconbar curIcon: (GKIconbar at: ICON_LOOK))
		(theGame setCursor: ((GKIconbar curIcon?) cursor?) TRUE)
		((inventory at: iMagGlass) message: V_LOOK)
		(Bset fUsingMagnifyingGlass)
		(= priority 15)
		(if (timers size?)
			(= newEventHandler (EventHandler new:))
			(= i 0)
			(while (< i (timers size?))
				(newEventHandler add: (timers at: i))
				(++ i)
			)
			(timers release:)
		)
		(super init: &rest)
		(if local0
			(magFrame init: stopUpd: setPri: 15)
		)
	)
	
	(method (dispose &tmp i temp1)
		(inventory theCaller: local2)
		(if newEventHandler
			(= i 0)
			(while (< i (newEventHandler size?))
				(timers add: (newEventHandler at: i))
				(++ i)
			)
		)
		(if local0
			(magFrame dispose:)
		)
		(restoreIB doit:)
		(super dispose:)
		(if (theGame keepBar?)
			(theIconBar draw:)
		)
		(Bclr fUsingMagnifyingGlass)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(Animate (cast elements?) TRUE)
		((inventory at: iMagGlass) message: V_MAG_GLASS)
		(GKIconbar curIcon: theIcon)
		(inventory showSelf: (inventory curPage?))
	)
	
	(method (doVerb theVerb)
		(if msgType
			(cond 
				((Message MsgGet modNum noun theVerb NULL 1)
					(messager say: noun theVerb NULL 0 0 modNum)
				)
				((== theVerb V_MAG_GLASS)
					(messager say: NULL V_MAG_GLASS C_ALREADY_USING_GLASS 0 0 15)
				)
				(else
					(messager say: NULL theVerb NULL 0 0 0)
				)
			)
		)
		(return TRUE)
	)
)

(instance checkIcon of Code
	
	(method (doit thisIcon &tmp i)
		(if
			(and
				(thisIcon isKindOf: IconItem)
				(not (& (thisIcon signal?) DISABLED))
			)
			(= [local4 (theIconBar indexOf: thisIcon)] 1)
		)
	)
)

(instance restoreIB of Code
	
	(method (doit &tmp i)
		(for ((= i 0)) (< i 14) ((++ i))
			(if (== [local4 i] 1)
				((theIconBar at: i)
					signal: (& ((theIconBar at: i) signal?) $fffb)
				)
			)
		)
	)
)
