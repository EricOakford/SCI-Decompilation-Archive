;;; Sierra Script 1.0 - (do not remove this comment)
(script# 801)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Invent)
(use Menu)
(use System)

(public
	DebugMenu 0
)

(enum
$600 debugM
	egoI
	roomI
	memI
	addToPicsI
		divider601I
	tglDebugI
	clicksI
	getInvI
	setVarI
	getAllI
	logI
		divider602I
	visualI
	priorityI
	controlI
)

(instance DebugMenu of Script
	
	(method (init)
		(AddMenu
			{ Debug_}
			{Ego info...`@e:Show Room`@R:Free Mem`@F:Show ATPs`@P:-! :Set Debug`^D:Clicks:Set Inv`@S:Set Var`@V:Get All`@G:Log`@L:-! :Visual`#0:Priority`#6:Control`#4}
		)
		(if (not (StatusLine state?))
			(TheMenuBar draw:)
		)
	)
	
	(method (doit event &tmp oldPause [temp1 6] [buf 33])
		(switch event
			(egoI
				(Print
					(Format @str 801 0 (ego x?) (ego y?) (ego priority?))
					#icon (ego view?) (ego loop?) (ego cel?)
				)
			)
			(roomI
				(Print (Format @buf 801 1 curRoomNum))
			)
			(memI
				(theGame showMem:)
			)
			(addToPicsI
				(addToPics showSelf:)
			)
			(tglDebugI
				;EO: Changed to SetDebug so that I can use the internal debugger without SHIFT-SHIFT-+
				;(my laptop doesn't have all of the required keys)
				(SetDebug)
			;	(= debugOn TRUE)
			)
			(clicksI
				(if (^= debugging TRUE)
					(Print 801 2)
				)
			)
			(getInvI
				(= str 0)
				(GetInput @str 8 {Inv. Object})
				(= global111 (ReadNumber @str))
				(= str 0)
				(GetInput @str 12 {Owner})
				(if (not (StrCmp {ego} @str))
					((inventory at: global111) moveTo: ego)
				else
					((inventory at: global111) moveTo: (ReadNumber @str))
				)
				(= str 0)
			)
			(setVarI
				(= str 0)
				(GetInput @str 8 {Variable No.})
				(= global111 (ReadNumber @str))
				(= str 0)
				(GetInput @str 8 {Value})
				(= [isNightTime (- global111 100)] (ReadNumber @str))
				(= str 0)
			)
			(getAllI
				(inventory eachElementDo: #moveTo ego)
				((Inventory at: iCupidBow) loop: 0 cel: 0)
			)
			(logI
				(if (not writingLog)
					(if (< (= logEntries (FOpen {KQ4QA.LOG} 0)) 0)
						(Print 801 3)
					else
						(= writingLog TRUE)
						(Print 801 4)
						(FPuts logEntries {New entries start here.\0D\n})
					)
				else
					(Print 801 5)
				)
			)
			(visualI
				(Show VMAP)
			)
			(priorityI
				(Show PMAP)
			)
			(controlI
				(Show CMAP)
				(Animate (cast elements?))
				(while (== 0 ((= oldPause (Event new: allEvents)) type?))
					(oldPause dispose:)
				)
				(oldPause dispose:)
				(Show VMAP)
			)
		)
	)
)
