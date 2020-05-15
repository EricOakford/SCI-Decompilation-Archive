;;; Sierra Script 1.0 - (do not remove this comment)
(script# LLINV) ;814
(include game.sh)
(use Main)
(use Intrface)
(use BordWind)
(use IconBar)
(use Invent)
(use System)

(public
	invCode 0
)

(instance invCode of Code
	(properties)
	
	(method (init &tmp temp0)
		(Inventory
			init:
			add:
				wallet
				breathSpray
				watch
				apple
				ring
				whiskey
				remoteControl
				rose
				lubber
				candy
				discoPass
				pocketKnife
				wine
				magazine
				hammer
				pills
				ribbon
				graffiti
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor myInsideColor
			eachElementDo: #init
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(watch owner: ego)
		(wallet owner: ego)
		(breathSpray owner: ego)
		(invWin
			color: 0
			back: myBackColor
			topBordColor: myBackColor
			lftBordColor: myBackColor
			rgtBordColor: myBackColor
			botBordColor: myBackColor
			insideColor: myInsideColor
			topBordColor2: myTopBordColor
			lftBordColor2: myTopBordColor
			botBordColor2: myBotBordColor
			rgtBordColor2: myBotBordColor
		)
	)
)

(instance invWin of InsetWindow
	(properties
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open)
		(invLook
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 68)
		)
		(super open:)
	)
)

(instance invLook of IconItem
	(properties
		view 851
		loop 2
		cel 0
		nsTop 0
		cursor 101
		message verbLook
		signal (| FIXED_POSN RELVERIFY)
		helpStr {To look more closely at an object, first click here, then click on the item.}
	)
	
	(method (init)
		(self lowlightColor: myRgtBordColor)
		(super init:)
	)
)

(instance invHand of IconItem
	(properties
		view 851
		loop 0
		cel 0
		cursor 102
		message verbDo
		helpStr {Use this when you want to do something to an item.}
	)
	
	(method (init)
		(self lowlightColor: myTextColor16)
		(super init:)
	)
)

(instance invSelect of IconItem
	(properties
		view 851
		loop 4
		cel 0
		cursor ARROW_CURSOR
		helpStr {To use an item in the game, first click here, then click on the item, then click on OK.}
	)
	
	(method (init)
		(self lowlightColor: myBotBordColor)
		(super init:)
	)
)

(instance invHelp of IconItem
	(properties
		view 851
		loop 1
		cel 0
		cursor 150
		message verbHelp
		helpStr {This icon tells you about other icons.}
	)
	
	(method (init)
		(self lowlightColor: myTextColor9)
		(super init:)
	)
)

(instance ok of IconItem
	(properties
		view 851
		loop 3
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Click here to close this window and return to the game.}
	)
	
	(method (init)
		(self lowlightColor: myTextColor13)
		(super init:)
	)
)

(class LLinvItem of InvItem
	(properties)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print
					(+ 850 (Inventory indexOf: self))
					(Inventory indexOf: self)
					#icon 900 loop cel
				)
				(UnLoad TEXT (+ 850 (Inventory indexOf: self)))
			)
			(verbDo
				(Printf 814 0 (self description?))
			)
			(verbUse
				(if (!= (Inventory indexOf: self) theItem)
					(Print (+ 850 theItem) (Inventory indexOf: self))
				else
					(Print 814 1)
				)
				(UnLoad TEXT (+ 850 theItem))
			)
		)
	)
)

(instance wallet of LLinvItem
	(properties
		view 900
		cursor 0
		description {your wallet}
	)
	
	(method (doVerb theVerb theItem &tmp temp0 [str 100])
		(switch theVerb
			(verbLook
				(if (or (== curRoomNum 250) (== curRoomNum 260))
					(Print 814 2)
				else
					(Print (Format @str 814 3 dollars) #icon view loop cel)
				)
			)
			(verbDo
				(switch
					(Print 814 4
						#icon view loop cel
						#button {Credit Cards} 0
						#button {Business Cards} 1
						#button {Notes} 2
					)
					(0 (Print 814 5))
					(1 (Print 814 6))
					(2 (Print 814 7))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance breathSpray of LLinvItem
	(properties
		view 900
		cel 1
		cursor 1
		description {your breath spray}
	)
)

(instance watch of LLinvItem
	(properties
		view 900
		cel 2
		cursor 2
		description {your wrist watch}
	)
	
	(method (doVerb theVerb theItem &tmp [str 100])
		(switch theVerb
			(verbLook
				(Print
					(Format @str 814 8
						(>> currentTime $000c)
						(& (>> currentTime $0006) $003f)
					)
					#icon view loop cel
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance apple of LLinvItem
	(properties
		view 900
		loop 2
		cursor 3
		description {the apple}
		owner 300
	)
)

(instance ring of LLinvItem
	(properties
		view 900
		loop 2
		cel 1
		cursor 4
		description {the diamond ring}
		owner 130
	)
)

(instance whiskey of LLinvItem
	(properties
		view 900
		loop 2
		cel 2
		cursor 5
		description {your glass of whiskey}
		owner 110
	)
)

(instance remoteControl of LLinvItem
	(properties
		view 900
		loop 2
		cel 3
		cursor 6
		description {your remote control}
		owner 120
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 814 9)
				(Print 814 10)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance rose of LLinvItem
	(properties
		view 900
		loop 2
		cel 4
		cursor 7
		description {the rose}
		owner 120
	)
)

(instance lubber of LLinvItem
	(properties
		view 900
		loop 2
		cel 5
		cursor 8
		description {your prophylactic}
		owner 510
	)
)

(instance candy of LLinvItem
	(properties
		view 900
		loop 2
		cel 6
		cursor 9
		description {the box of candy}
		owner 150
	)
)

(instance discoPass of LLinvItem
	(properties
		view 900
		loop 2
		cel 7
		cursor 10
		description {the disco pass}
		owner 330
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 814 11 #mode teJustCenter #icon view loop cel)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance pocketKnife of LLinvItem
	(properties
		view 900
		loop 2
		cel 8
		cursor 11
		description {your pocketknife}
		owner 500
	)
)

(instance wine of LLinvItem
	(properties
		view 900
		loop 2
		cel 9
		cursor 12
		description {your wine}
		owner 510
	)
)

(instance magazine of LLinvItem
	(properties
		view 900
		loop 2
		cel 10
		cursor 13
		description {your magazine}
		owner 510
	)
	
	(method (doVerb theVerb theItem)
		(if (or (== theVerb verbLook) (== theVerb verbDo))
			(Print 814 12)
			(Print 814 13)
			(SolvePuzzle fReadMagazine 1)
			(Print 814 14 #icon view loop 15)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance hammer of LLinvItem
	(properties
		view 900
		loop 2
		cel 11
		cursor 14
		description {Lefty's hammer}
		owner 160
	)
)

(instance pills of LLinvItem
	(properties
		view 900
		loop 2
		cel 12
		cursor 15
		description {the bottle of pills}
		owner 160
	)
)

(instance ribbon of LLinvItem
	(properties
		view 900
		loop 2
		cel 13
		cursor 16
		description {the ribbon}
		owner 390
	)
)

(instance graffiti of LLinvItem
	(properties
		view 900
		loop 2
		cel 14
		cursor 17
		description {the password}
		owner 130
	)
)
