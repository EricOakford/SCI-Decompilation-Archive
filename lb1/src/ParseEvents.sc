;;; Sierra Script 1.0 - (do not remove this comment)
(script# 413)
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	isInvItem 0
)

(local
	local0 = [2 64 512 8 16 1 4 1024 32 256 128]
	myName = [413 0 413 1 413 2 413 3 413 4 413 5 413 6 413 7 413 8 413 9 413 10]
	nameSpec = ['/celie' '/clarence' '/henri' '/ethel' '/fifi' '/gertie' '/gloria' '/jeeves' '/lillian' '/rudolph' '/wilbur']
	actor1Spec = ['/*<celie' '/*<clarence' '/*<henri' '/*<ethel' '/*<fifi' '/*<gertie' '/*<gloria' '/*<jeeves' '/*<lillian' '/*<rudolph' '/*<wilbur']
	actor2Spec = ['//celie' '//attorney' '//colonel' '//ethel' '//fifi' '//gertie' '//actress' '//butler' '//lil' '//rudolph' '//c']
	inv1Spec = ['//necklace' '//monocle' '//lantern' '//can' '//pin' '//key<skeleton' '//poker' '//crowbar' '//butt,butt' '//record' '//notebook,pencil' '//cracker,box' '//bone' '//handle,valve' '//bullet' '//derringer' '//match' '//carrot' '//key' '//diary' '//control' '//cane' '//diamond' '//handkerchief']
	inv2Spec = ['<necklace' '<monocle' '<lantern' '<can' '<pin' '<key<skeleton' '<poker' '<crowbar' '<butt,butt' '<record' '<notebook,pencil' '<cracker,box' '<bone' '<handle,valve' '<bullet' '<derringer' '<match' '<carrot' '<key' '<diary' '<control' '<cane' '<diamond' '<handkerchief']
	saidIndex
)
(procedure (SaidEvent event &tmp i whom [str 50])
	(for ((= i 0)) (< i 11) ((++ i))
		(if (and (Said 'tell//*<about>') (Said [actor2Spec i]))
			(++ saidIndex)
			(event claimed: FALSE)
		)
		(if
			(and
				(or
					(and
						(Said 'hold,hand,flirt>')
						(or (Said [actor1Spec i]) (Said [actor2Spec i]))
					)
					(Said [nameSpec i])
				)
				(not
					(& (| global195 global208)
						(= whom [local0 i])
					)
				)
			)
			(= i (* i 2))
			(if
				(or
					(and (== whom 1) (& deadGuests $0001))
					(and (== whom 128) (& deadGuests $0002))
					(and (== whom 4) (& deadGuests $0004))
					(and (== whom 8) (& deadGuests $0008))
					(and
						(or (== whom 16) (== whom 1024))
						(& deadGuests $0010)
					)
					(and (== whom 64) (& deadGuests $0020))
					(and (== whom 32) (& deadGuests $0040))
				)
				(Print
					(Format @str 413 13
						[myName i]
						[myName (++ i)]
					)
				)
			else
				(Print
					(Format @str 413 14
						[myName i]
						[myName (++ i)]
					)
				)
			)
			(return TRUE)
		)
	)
	(event claimed: FALSE)
	(return FALSE)
)

(instance isInvItem of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 413)
	)
	
	(method (handleEvent event &tmp node temp1)
		(= theInvItem (= haveInvItem 0))
		(= whichItem -1)
		(cond 
			((or (Said '/dijon') (Said '//dijon'))
				(Print 413 11)
			)
			(
				(and
					(Said
						'ask,tell,hold,deliver,converse,examine,get,hit,kill,kiss,embrace,flirt>'
					)
					(SaidEvent event)
				)
			)
			((or (Said 'examine//*<for') (Said 'find'))
				(Print 413 12)
			)
			((= theInvItem (inventory saidMe:))
				(= node (inventory first:))
				(= whichItem 0)
				(while (!= theInvItem (NodeValue node))
					(= node (inventory next: node))
					(++ whichItem)
				)
				(= whichItem (inventory indexOf: theInvItem))
				(event claimed: FALSE)
			)
			(else
				(= whichItem 0)
				(while (<= whichItem iHandkerchief)
					(if
						(or
							(Said [inv1Spec whichItem])
							(Said [inv2Spec whichItem])
						)
						(= theInvItem (inventory at: whichItem))
						(event claimed: FALSE)
						(break)
					)
					(++ whichItem)
				)
			)
		)
		(if (and theInvItem (== (theInvItem owner?) ego))
			(= haveInvItem TRUE)
		)
		(if (== saidIndex 1)
			(Bset fSawDeadGuest)
		else
			(Bclr fSawDeadGuest)
		)
		(client setScript: 0)
	)
)
