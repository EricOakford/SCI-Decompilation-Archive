;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include sci.sh)
(use Main)
(use Invent)
(use System)

(public
	doCode 0
	useCode 1
)

(instance doCode of Code
	(properties)
	
	(method (init &tmp temp0 temp1 inventoryCurIcon inventoryCurIconView inventoryCurIconLoop inventoryCurIconCel temp6)
		(= temp1 0)
		(= temp0 0)
		(while (< temp0 57)
			(if ((inventory at: temp0) chestAmout?)
				(= temp1 1)
				(= temp0 57)
			)
			(++ temp0)
		)
		(if temp1
			(inventory showSelf: curRoom)
			(if
				(and
					(= inventoryCurIcon (inventory curIcon?))
					(inventoryCurIcon isKindOf: InvI)
				)
				(inventoryCurIcon
					chestAmout: (- (inventoryCurIcon chestAmout?) 1)
				)
				(inventoryCurIcon
					amount: (+ (inventoryCurIcon amount?) 1)
				)
				(= inventoryCurIconView (inventoryCurIcon view?))
				(= inventoryCurIconLoop (inventoryCurIcon loop?))
				(= inventoryCurIconCel (inventoryCurIcon cel?))
				((= temp6 (ScriptID 36 2))
					cursorView: inventoryCurIconView
				)
				(temp6 cursorLoop: inventoryCurIconLoop)
				(temp6 cursorCel: inventoryCurIconCel)
				((= inventoryCurIcon (ScriptID 36 1))
					view: inventoryCurIconView
					loop: inventoryCurIconLoop
					cel: inventoryCurIconCel
					show:
				)
				(UpdateScreenItem inventoryCurIcon)
			)
		else
			(messager say: 1 6 1 1 0 29)
		)
	)
)

(instance useCode of Code
	(properties)
	
	(method (init param1 &tmp temp0)
		(= param1 (localproc_0018 param1))
		(= temp0 (inventory at: param1))
		(cond 
			((== param1 0) (messager say: 1 6 2 1 0 29))
			((== param1 14) (messager say: 1 6 6 1 0 29))
			((== param1 25) (messager say: 1 6 7 1 0 29))
			((== param1 16) (messager say: 1 6 9 1 0 29))
			((OneOf param1 53 56 52 55 54) (messager say: 1 6 10 1 0 29))
			(
			(and (== param1 5) (== ((inventory at: 5) amount?) 1)) (messager say: 1 6 12 1 0 29))
			((== param1 19) (messager say: 1 6 11 1 0 29))
			((== param1 18) (messager say: 1 6 13 1 0 29))
			((== param1 44) (messager say: 1 6 14 1 0 29))
			((== param1 45) (messager say: 1 6 15 1 0 29))
			((== param1 17) (messager say: 1 6 16 1 0 29))
			(else
				(temp0 roomGets:)
				(theIconBar curInvIcon: 0)
				(if (> (temp0 amount?) 0)
					(messager say: 1 6 5 1 0 29)
				else
					(messager say: 1 6 4 1 0 29)
				)
			)
		)
	)
)
