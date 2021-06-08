;;; Sierra Script 1.0 - (do not remove this comment)
(script# LLINV)
(include game.sh)
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
	[monthIndex 13] = [0 {January} {February} {March} {April} {May} {June} {July} {August} {September} {October} {November} {December}]
)
(procedure (CombineItems theString param2 &tmp temp0)
	(TimePrint
		(+ param2 (if playingAsLarry 50 else 80))
		theString
	)
)

(class LLIconItem of IconItem
	
	(method (ownedBy)
		(return FALSE)
	)
)

(instance invCode of Code
	
	(method (init)
		(Inventory
			init:
			add: ok
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
	)
	
	(method (doit theEgo)
		(switch theEgo
			(LARRY
				(Inventory
					add:
						Camcorder
						Battery_Charger
						A_Blank_Videotape_a
						A_Blank_Videotape_b
						A_Blank_Videotape_c
						Michelle_Milken_s_Resume
						Hard_Disk_Cafe_Napkin
						AeroDork_Gold_Card
						Boarding_Pass
						AeroDork_s_In-Flight_Magazine
						Some_Change
						DayTrotter_
						Money
						Credit_Cards
						Membership_Tape
						Lana_Luscious__Resume
						Tramp_Casino_Matchbook
						Silver_Dollar
						Roller-skates
						Chi_Chi_Lambada_s_Resume
						Doc_Pulliam_s_Card
						Green_Card
						Doily
						invLook
						invHand
						invSelect
						invHelp
						ok
					eachElementDo: #highlightColor myHighlightColor
					eachElementDo: #lowlightColor myInsideColor
					eachElementDo: #init
					state: NOCLICKHELP
				)
			)
			(PATTI
				(Inventory
					add:
						DataMan
						Reverse_Biaz_DataPak
						P__C__Hammer_DataPak
						Reverse_Biaz_Fax
						Champagne
						Gold_Record
						Cassette_Tape
						P__C__Hammer_Fax
						Letter_Opener
						Desk_Key
						Folder_Of_Evidence
						Photocopied_Evidence
						Reel_To_Reel_Tape
						Hooter_Shooter
						invLook
						invHand
						invSelect
						invHelp
						ok
					eachElementDo: #highlightColor myHighlightColor
					eachElementDo: #lowlightColor myInsideColor
					eachElementDo: #init
					state: NOCLICKHELP
				)
			)
		)
	)
)

(instance invWin of InsetWindow
	(properties
		priority -1
		topBordHgt 28
		botBordHgt 5
	)
	
	(method (open)
		(invLook nsLeft: (- (/ (- (self right?) (self left?)) 2) 68))
		(super open:)
	)
)

(instance ok of LLIconItem
	(properties
		view 991
		loop 3
		cel 0
		cursor 999
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Click here to close this window and return to the game.}
	)
	
	(method (init)
		(= highlightColor 0)
		(= lowlightColor gLowlightColor_6)
		(super init:)
	)
)

(instance invLook of LLIconItem
	(properties
		view 991
		loop 2
		cel 0
		cursor 1
		message verbLook
		signal (| FIXED_POSN RELVERIFY)
		helpStr {To look more closely at one of your inventory items, first click here, then click on the item.}
	)
	
	(method (init)
		(= highlightColor 0)
		(= lowlightColor myLowlightColor2)
		(super init:)
	)
)

(instance invHand of LLIconItem
	(properties
		view 991
		loop 0
		cel 0
		cursor 2
		message verbDo
		helpStr {To do something to one of your inventory items, first click here, then click on the item.}
	)
	
	(method (init)
		(= highlightColor 0)
		(= lowlightColor gLowlightColor_3)
		(super init:)
	)
)

(instance invHelp of LLIconItem
	(properties
		view 991
		loop 1
		cel 0
		cursor 9
		message verbHelp
		helpStr {To learn about the other icons in this window, first click here, then pass the question mark over the other icons.}
	)
	
	(method (init)
		(= highlightColor 0)
		(= lowlightColor gLowlightColor_4)
		(super init:)
	)
)

(instance invSelect of LLIconItem
	(properties
		view 991
		loop 4
		cel 0
		cursor ARROW_CURSOR
		helpStr {To use one of your inventory items in the game, first click here, then click on the item, then click on OK.}
	)
	
	(method (init)
		(= highlightColor 0)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
)

(instance Camcorder of InvItem
	(properties
		view 931
		cursor 931
		signal IMMEDIATE
		description {the camcorder}
	)
	
	(method (doVerb theVerb theItem &tmp [str 50] temp50 temp51)
		(= temp50 (mod state 100))
		(switch theVerb
			(verbLook
				(TimePrint 19 0
					#width 250
					#title name
				)
				(Format @str 19 1
					(if (>= state 100) {On} else {Off})
					(/ (cameraTimer seconds?) 60)
					(mod (cameraTimer seconds?) 60)
				)
				(TimePrint @str 70 123
					#mode teJustCenter
					#title name
				)
			)
			(verbDo
				(cond 
					((>= state 100)
						(= state (- state 100))
						(TimePrint 19 2)
						(Format @str 19 3
							(/ (cameraTimer seconds?) 60)
							(mod (cameraTimer seconds?) 60)
						)
						(TimePrint @str)
					)
					((and temp50 (cameraTimer seconds?))
						(= state (+ state 100))
						(SolvePuzzle 4 fTurnOnCamera)
						(TimePrint 19 4)
						(Format @str 19 3
							(/ (cameraTimer seconds?) 60)
							(mod (cameraTimer seconds?) 60)
						)
						(TimePrint @str)
					)
					(temp50
						(TimePrint 19 5)
					)
					(else
						(TimePrint 19 6)
					)
				)
			)
			(verbUse
				(if (OneOf theItem verbLook verbDo verbUse)
					(if temp50
						(TimePrint 19 7)
						((Inventory at: temp50) owner: 23)
					)
					(if (!= ((Inventory at: theItem) state?) 1)
						(TimePrint 19 8)
						(TimePrint 19 9
							#at -1 185
						)
						(= state 0)
					else
						(SolvePuzzle 4 fPutTapeInCamera)
						(TimePrint 19 10)
						(= state theItem)
						((Inventory at: theItem) owner: Camcorder)
						(Inventory curIcon: 0 hide:)
						(ego showInv:)
					)
				else
					(CombineItems (Inventory indexOf: self) theItem)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Battery_Charger of InvItem
	(properties
		view 932
		cursor 932
		signal IMMEDIATE
		description {the battery charger}
		name "Battery Charger"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 11
					#title name
				)
			)
			(verbDo
				(TimePrint 19 12)
			)
			(verbUse
				(switch theItem
					(iCamcorder
						(TimePrint 19 13)
					)
					(else 
						(CombineItems (Inventory indexOf: self) theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance A_Blank_Videotape_a of InvItem
	(properties
		view 934
		cursor 934
		signal IMMEDIATE
		description {the videotape}
		name "A Blank Videotape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch state
					(tapeBLANK
						(TimePrint 19 14
							#title name
							#mode teJustCenter
						)
					)
					(tapeERASED
						(TimePrint 19 15
							#title name
						)
					)
					(tapeMICHELLE
						(TimePrint 19 16
							#title name
						)
					)
					(tapeLANA
						(TimePrint 19 17
							#title name
						)
					)
					(tapeCHICHI
						(TimePrint 19 18
							#title name
						)
					)
				)
			)
			(verbDo
				(TimePrint 19 19)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance A_Blank_Videotape_b of InvItem
	(properties
		view 934
		cursor 934
		signal IMMEDIATE
		description {the videotape}
		name "A Blank Videotape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch state
					(tapeBLANK
						(TimePrint 19 14
							#title name
							#mode teJustCenter
						)
					)
					(tapeERASED
						(TimePrint 19 15
							#title name
						)
					)
					(tapeMICHELLE
						(TimePrint 19 16
							#title name
						)
					)
					(tapeLANA
						(TimePrint 19 17
							#title name
						)
					)
					(tapeCHICHI
						(TimePrint 19 18
							#title name
						)
					)
				)
			)
			(verbDo
				(TimePrint 19 19)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance A_Blank_Videotape_c of InvItem
	(properties
		view 934
		cursor 934
		signal IMMEDIATE
		description {the videotape}
		name "A Blank Videotape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch state
					(tapeBLANK
						(TimePrint 19 14
							#title name
							#mode teJustCenter
						)
					)
					(tapeERASED
						(TimePrint 19 15
							#title name
						)
					)
					(tapeMICHELLE
						(TimePrint 19 16
							#title name
						)
					)
					(tapeLANA
						(TimePrint 19 17
							#title name
						)
					)
					(tapeCHICHI
						(TimePrint 19 18
							#title name
						)
					)
				)
			)
			(verbDo
				(TimePrint 19 19)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Michelle_Milken_s_Resume of InvItem
	(properties
		view 937
		cursor 937
		signal IMMEDIATE
		description {Michelle's resume}
		name "Michelle Milken's Resume"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 22
					#font resumeFont
					#icon 937 2 1
					#title name
				)
				(TimePrint 19 23
					#font resumeFont
					#width 222
					#title name
				)
				(if (not (OneOf (Hard_Disk_Cafe_Napkin owner?) 23 510))
					(SolvePuzzle 1 fFoundNapkin)
					(TimePrint 19 24)
					(ego get: iNapkin)
					(Inventory curIcon: 0 hide:)
					(ego showInv:)
				)
			)
			(verbDo
				(TimePrint 19 25)
				(self doVerb: verbLook)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Hard_Disk_Cafe_Napkin of InvItem
	(properties
		view 938
		cursor 938
		signal IMMEDIATE
		description {the Hard Disk Cafe napkin}
		name "Hard Disk Cafe Napkin"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 26
					#title name
					#width 222
					#mode teJustCenter
				)
			)
			(verbDo
				(TimePrint 19 27)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance AeroDork_Gold_Card of InvItem
	(properties
		view 939
		cursor 939
		signal IMMEDIATE
		description {the AeroDork Gold Card}
		name "AeroDork Gold Card"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 28
					#title name
				)
			)
			(verbDo
				(TimePrint 19 29)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Boarding_Pass of InvItem
	(properties
		view 940
		cursor 940
		signal IMMEDIATE
		description {the boarding pass}
		name "Boarding Pass"
	)
	
	(method (doVerb theVerb theItem &tmp [str 200] sysDate month day year)
		(switch theVerb
			(verbLook
				(= day (& (= sysDate (GetTime SYSDATE)) $001f))
				(= month (& (>> sysDate $0005) $000f))
				(= year (+ (>> sysDate $0009) 1980))
				(Format @str 19 30
					(switch currentCity
						(LOS_ANGELES {L.A.})
						(NEW_YORK {New York})
						(ATLANTIC_CITY {Atlantic City})
						(MIAMI {Miami})
					)
					(switch state
						(LOS_ANGELES {L.A.})
						(NEW_YORK {New York})
						(ATLANTIC_CITY {Atlantic City})
						(MIAMI {Miami})
					)
					(/ seatNum 100)
					(mod seatNum 100)
					[monthIndex month]
					day
					year
				)
				(TimePrint @str #title name)
			)
			(verbDo
				(TimePrint 19 31)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance AeroDork_s_In-Flight_Magazine of InvItem
	(properties
		view 941
		cursor 941
		signal IMMEDIATE
		description {the magazine}
		name "AeroDork's In-Flight Magazine"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 32
					#title name
				)
			)
			(verbDo
				(TimePrint 19 33)
				(SolvePuzzle 5 fReadMagazine)
				(TimePrint 19 34)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Some_Change of InvItem
	(properties
		view 942
		cursor 942
		signal IMMEDIATE
		description {the quarter}
		name "Some Change"
	)
	
	(method (doVerb theVerb theItem &tmp [str 40])
		(switch theVerb
			(verbLook
				(Format @str 19 35 (* numQuarters 2))
				(TimePrint @str #title name)
			)
			(verbDo
				(Format @str 19 36
					(if (Random 0 1)
						{heads}
					else
						{tails}
					)
				)
				(TimePrint @str)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance DayTrotter_ of InvItem
	(properties
		view 943
		cursor 943
		signal IMMEDIATE
		description {the DayTrotter\05}
		name "DayTrotter\05"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if state
					(TimePrint 19 37 #title name)
				else
					(SolvePuzzle 11 fSearchDayTrotter)
					(TimePrint 19 38
						#title name
					)
					(Inventory curIcon: 0 hide:)
					(= state 1)
					(ego get: iMoney iCreditCards showInv:)
				)
			)
			(verbDo
				(if state
					(TimePrint 19 39 #title name)
				else
					(self doVerb: verbLook)
				)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Money of InvItem
	(properties
		view 944
		cursor 944
		signal IMMEDIATE
		description {the money}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 40 #title name)
			)
			(verbDo
				(TimePrint 19 41)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Credit_Cards of InvItem
	(properties
		view 945
		cursor 945
		signal IMMEDIATE
		description {the credit cards}
		name "Credit Cards"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 42 #title name)
			)
			(verbDo
				(TimePrint 19 43)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Membership_Tape of InvItem
	(properties
		view 946
		cursor 946
		signal IMMEDIATE
		description {the membership tape}
		name "Membership Tape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch state
					(HDCgotTape
						(TimePrint 19 44 #title name)
					)
					(HDCbribed
						(TimePrint 19 45 #title name)
					)
					(HDChacked
						(TimePrint 19 46 #title name)
					)
				)
			)
			(verbDo
				(TimePrint 19 47)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance DataMan of InvItem
	(properties
		view 948
		cursor 948
		signal IMMEDIATE
		description {the DataMan}
		owner 445
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch state
					(datamanEMPTY
						(TimePrint 19 48 #title name)
					)
					(datamanBIAZ
						(TimePrint 19 49 #title name #mode teJustCenter)
						(Bset fKnowBiazAddress)
					)
					(datamanHAMMER
						(SolvePuzzle 1 fReadHammerDataPak)
						(TimePrint 19 50 #title name #mode teJustCenter)
					)
				)
			)
			(verbDo
				(switch state
					(datamanEMPTY
						(TimePrint 19 51)
					)
					(datamanBIAZ
						(= state 0)
						(TimePrint 19 52)
						(Reverse_Biaz_DataPak owner: 24)
						(Inventory curIcon: 0 hide:)
						(ego showInv:)
					)
					(datamanHAMMER
						(= state 0)
						(TimePrint 19 53)
						(P__C__Hammer_DataPak owner: 24)
						(Inventory curIcon: 0 hide:)
						(ego showInv:)
					)
				)
			)
			(verbUse
				(switch theItem
					(iBiazPak
						(SolvePuzzle 7 fPutPakInDataMan)
						(if (== (DataMan state?) 0)
							(TimePrint 19 54)
							(= state 1)
							(Reverse_Biaz_DataPak owner: self)
							(Inventory curIcon: 0 hide:)
							(ego showInv:)
						else
							(TimePrint 19 55)
						)
					)
					(iHammerPak
						(SolvePuzzle 7 fPutPakInDataMan)
						(if (== (DataMan state?) 0)
							(TimePrint 19 56)
							(= state 2)
							(P__C__Hammer_DataPak owner: self)
							(Inventory curIcon: 0 hide:)
							(ego showInv:)
						else
							(TimePrint 19 55)
						)
					)
					(else 
						(CombineItems (Inventory indexOf: self) theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Reverse_Biaz_DataPak of InvItem
	(properties
		view 949
		cursor 949
		signal IMMEDIATE
		description {the Reverse Biaz DataPak}
		owner 445
		name "Reverse Biaz DataPak"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 57 #title name #mode teJustCenter)
			)
			(verbDo
				(TimePrint 19 58)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance P__C__Hammer_DataPak of InvItem
	(properties
		view 949
		cursor 949
		signal IMMEDIATE
		description {the P. C. Hammer DataPak}
		owner 445
		name "P. C. Hammer DataPak"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 59 #title name #mode teJustCenter)
			)
			(verbDo
				(TimePrint 19 58)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Lana_Luscious__Resume of InvItem
	(properties
		view 937
		cursor 937
		signal IMMEDIATE
		description {Lana's resume}
		name "Lana Luscious' Resume"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 60
					#font resumeFont
					#icon 937 2 2
					#title name
				)
				(TimePrint 19 61
					#font resumeFont
					#width 277
					#title name
				)
				(if (not (ego has: iMatchbook))
					(SolvePuzzle 1 fFindMatchbook)
					(TimePrint 19 62)
					(ego get: iMatchbook)
					(Inventory curIcon: 0 hide:)
					(ego showInv:)
				)
			)
			(verbDo
				(TimePrint 19 63)
				(self doVerb: verbLook)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Tramp_Casino_Matchbook of InvItem
	(properties
		view 952
		cursor 952
		signal IMMEDIATE
		description {the Tramp's casino matchbook}
		name "Tramp Casino Matchbook"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 64 #title name #mode teJustCenter)
			)
			(verbDo
				(TimePrint 19 65)
				(TimePrint 19 66
					#at -1 185
				)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Silver_Dollar of InvItem
	(properties
		view 953
		cursor 953
		signal IMMEDIATE
		description {the silver dollar}
		name "Silver Dollar"
	)
	
	(method (doVerb theVerb theItem &tmp [str 200])
		(switch theVerb
			(verbLook
				(Format @str 19 67
					silverDollars
					(if (> silverDollars 1) {s} else {})
				)
				(TimePrint @str #title name)
				(TimePrint 19 68)
			)
			(verbDo
				(TimePrint 19 69)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Roller-skates of InvItem
	(properties
		view 954
		cursor 954
		signal IMMEDIATE
		description {the roller-blades}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 70 #title name)
			)
			(verbDo
				(if (== curRoomNum 760)
					((ScriptID 760 1) doVerb: verbUse 18)
				else
					(TimePrint 19 71)
				)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Reverse_Biaz_Fax of InvItem
	(properties
		view 955
		cursor 955
		signal IMMEDIATE
		description {the fax on Reverse Biaz}
		name "Reverse Biaz Fax"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 49 #title name #mode teJustCenter)
				(Bset fKnowBiazAddress)
			)
			(verbDo
				(TimePrint 19 72)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Champagne of InvItem
	(properties
		view 956
		cursor 956
		signal IMMEDIATE
		description {the champagne}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 73 #title name)
			)
			(verbDo
				(TimePrint 19 74)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Gold_Record of InvItem
	(properties
		view 957
		cursor 957
		signal IMMEDIATE
		description {the gold record}
		name "Gold Record"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if state
					(TimePrint 19 75 #title name)
				else
					(TimePrint 19 76 #title name)
				)
			)
			(3 (TimePrint 19 77))
			(4
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Cassette_Tape of InvItem
	(properties
		view 958
		cursor 958
		signal IMMEDIATE
		description {the cassette tape}
		name "Cassette Tape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 78 #title name)
			)
			(verbDo
				(TimePrint 19 79)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Chi_Chi_Lambada_s_Resume of InvItem
	(properties
		view 937
		cursor 937
		signal IMMEDIATE
		description {Chi Chi's resume}
		name "Chi Chi Lambada's Resume"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 80
					#font resumeFont
					#icon 937 2 0
					#title name
				)
				(TimePrint 19 81
					#font resumeFont
					#width 240
					#title name
				)
				(if (not (ego has: iPulliamCard))
					(SolvePuzzle 1 fFindDentistCard)
					(TimePrint 19 82)
					(ego get: iPulliamCard)
					(Inventory curIcon: 0 hide:)
					(ego showInv:)
				)
			)
			(verbDo
				(TimePrint 19 83)
				(self doVerb: 2)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Doc_Pulliam_s_Card of InvItem
	(properties
		view 960
		cursor 960
		signal IMMEDIATE
		description {Doc Pulliam's card}
		name "Doc Pulliam's Card"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle 1 fReadDentishCard)
				(TimePrint 19 84
					#title name
					#width 222
					#mode teJustCenter
				)
			)
			(verbDo
				(TimePrint 19 85)
				(TimePrint 19 86)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Green_Card of InvItem
	(properties
		view 961
		cursor 961
		signal IMMEDIATE
		description {the green card}
		name "Green Card"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 87 #title name)
			)
			(verbDo
				(TimePrint 19 88)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Doily of InvItem
	(properties
		view 962
		cursor 962
		signal IMMEDIATE
		description {the doily}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 89 #title name)
				(TimePrint 19 90
					#at -1 185
					#title name
				)
				(TimePrint 19 91
					#at -1 185
					#title name
				)
			)
			(verbDo
				(cond 
					((!= curRoomNum 905)
						(TimePrint 19 92)
					)
					((ego sitting?)
						(TimePrint 19 93)
					)
					(else
						(if state
							(ego
								view: 907
								setLoop: 4
								setCel: 255
								cycleSpeed: 10
								setCycle: BegLoop curRoom
							)
							(TimePrint 19 94)
						else
							(ego
								view: 907
								setLoop: 4
								setCel: 0
								cycleSpeed: 10
								setCycle: EndLoop curRoom
							)
							(SolvePuzzle 8 fWoreDoily)
							(TimePrint 19 95)
						)
						(= state (not state))
					)
				)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance P__C__Hammer_Fax of InvItem
	(properties
		view 955
		cursor 955
		signal IMMEDIATE
		description {the fax on P. C. Hammer}
		name "P. C. Hammer Fax"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 96
					#title name
					#width 200
					#mode teJustCenter
				)
			)
			(verbDo
				(TimePrint 19 72)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Letter_Opener of InvItem
	(properties
		view 964
		cursor 964
		signal IMMEDIATE
		description {the letter opener}
		name "Letter Opener"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 97 #title name)
			)
			(verbDo
				(TimePrint 19 98)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Desk_Key of InvItem
	(properties
		view 965
		cursor 965
		signal IMMEDIATE
		description {the desk key}
		name "Desk Key"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 99 #title name)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Folder_Of_Evidence of InvItem
	(properties
		view 966
		cursor 966
		signal IMMEDIATE
		description {the folder of evidence}
		name "Folder Of Evidence"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle 4 fSearchEvidenceFolder)
				(TimePrint 19 100 #title name)
				(TimePrint 19 101 #title name)
			)
			(verbDo
				(if (ego has: iPhotocopiedEvidence)
					(TimePrint 19 102)
				else
					(TimePrint 19 103)
				)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Photocopied_Evidence of InvItem
	(properties
		view 967
		cursor 967
		signal IMMEDIATE
		description {the photocopies}
		name "Photocopied Evidence"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 19 104 #title name)
			)
			(verbDo
				(TimePrint 19 105)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Reel_To_Reel_Tape of InvItem
	(properties
		view 968
		cursor 968
		signal IMMEDIATE
		description {the reel-to-reel tape}
		name "Reel To Reel Tape"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if state
					(TimePrint 19 106 #title name)
				else
					(TimePrint 19 107 #title name)
					(TimePrint 19 108
						#at -1 185
					)
				)
			)
			(verbDo
				(TimePrint 19 109)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Hooter_Shooter of InvItem
	(properties
		view 969
		cursor 969
		signal IMMEDIATE
		description {the "Hooter Shooter" bra}
		name "Hooter Shooter"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle 2 fLookAtHooterShooter)
				(TimePrint 19 110 #title name)
			)
			(verbDo
				(self doVerb: verbLook)
			)
			(verbUse
				(CombineItems (Inventory indexOf: self) theItem)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
